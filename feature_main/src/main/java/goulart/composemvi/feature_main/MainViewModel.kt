package goulart.composemvi.feature_main

import goulart.composemvi.base_feature.core.BaseViewModel
import goulart.composemvi.base_feature.core.Reducer
import goulart.composemvi.base_feature.core.useCase
import goulart.composemvi.base_feature.model.main.MainScreenState
import goulart.composemvi.base_feature.model.main.MainScreenTaskItem
import goulart.composemvi.domain.entities.Task
import goulart.composemvi.domain.usecase.*
import goulart.composemvi.base_feature.mapper.TaskMapper
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : BaseViewModel<MainScreenState, MainScreenUiEvent>() {

    private val reducer = MainReducer(MainScreenState.initial())

    private val getTasks: GetTasksUseCase by useCase()
    private val insertTask: InsertTaskUseCase by useCase()
    private val updateTask: UpdateTaskUseCase by useCase()
    private val deleteTask: DeleteTaskUseCase by useCase()
    private val deleteAll: DeleteAllTasksUseCase by useCase()

    override val state: StateFlow<MainScreenState>
        get() = reducer.state

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        getTasks(
            onSuccess = { tasks ->
                sendEvent(
                    MainScreenUiEvent.ShowData(
                        TaskMapper.buildScreen(tasks)
                    )
                )
            }
        )
    }

    private fun sendEvent(event: MainScreenUiEvent) {
        reducer.sendEvent(event)
    }

    fun changeAddDialogState(show: Boolean) {
        sendEvent(MainScreenUiEvent.OnChangeDialogState(show))
    }

    fun addNewItem(title: String, body: String) {
        sendEvent(MainScreenUiEvent.OnChangeDialogState(false))
        insertTask(
            InsertTaskUseCase.Params(title, body),
            onSuccess = {
                fetchTasks()
            }
        )
    }

    fun onItemCheckedChanged(index: Int, task: MainScreenTaskItem) {
        updateTask(
            UpdateTaskUseCase.Params(TaskMapper.toDomain(task)),
            onSuccess = {
                sendEvent(MainScreenUiEvent.OnItemCheckedChanged(index, !task.isChecked))
            }
        )
    }

    fun remove(index: Int, task: MainScreenTaskItem) {
        deleteTask(
            params = DeleteTaskUseCase.Params(
                Task(
                    id = task.id,
                    title = task.title,
                    body = task.body,
                    isChecked = !task.isChecked
                )
            ),
            onSuccess = {
                sendEvent(MainScreenUiEvent.OnItemRemoved(index))
            }
        )
    }

    fun removeAll() {
        deleteAll(
            onSuccess = {
                sendEvent(
                    MainScreenUiEvent.ShowData(TaskMapper.buildScreen(emptyList()))
                )
            }
        )
    }

    private class MainReducer(initial: MainScreenState) : Reducer<MainScreenState, MainScreenUiEvent>(initial) {
        override fun reduce(oldState: MainScreenState, event: MainScreenUiEvent) {
            when (event) {
                is MainScreenUiEvent.OnChangeDialogState -> {
                    setState(oldState.copy(isShowAddDialog = event.show))
                }
                is MainScreenUiEvent.ShowData -> {
                    setState(oldState.copy(isLoading = false, data = event.items))
                }
                is MainScreenUiEvent.DismissDialog -> {
                    setState(oldState.copy(isShowAddDialog = false))
                }
                is MainScreenUiEvent.OnItemCheckedChanged -> {
                    val newList = oldState.data.toMutableList()
                    newList[event.index] = (newList[event.index] as MainScreenTaskItem).copy(isChecked = event.isChecked)
                    setState(oldState.copy(data = newList))
                }
                is MainScreenUiEvent.OnItemRemoved -> {
                    val newList = oldState.data.toMutableList()
                    newList.removeAt(event.index)
                    setState(oldState.copy(data = newList))
                }
            }
        }
    }
}