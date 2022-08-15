package goulart.composemvi.presentation.features.main

import goulart.composemvi.base.BaseViewModel
import goulart.composemvi.base.Reducer
import goulart.composemvi.base.TimeCapsule
import goulart.composemvi.domain.entities.ToDo
import goulart.composemvi.domain.usecase.*
import goulart.composemvi.presentation.base.useCase
import goulart.composemvi.presentation.mapper.MainScreenViewDataMapper
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
            onSuccess = { todos ->
                sendEvent(
                    MainScreenUiEvent.ShowData(
                        MainScreenViewDataMapper.buildScreen(todos)
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

    fun onItemCheckedChanged(index: Int, task: MainScreenItem.MainScreenTaskItem) {
        updateTask(
            UpdateTaskUseCase.Params(ToDo(
                id = task.id,
                title = task.title,
                body = task.body,
                isChecked = !task.isChecked
            )),
            onSuccess = {
                sendEvent(MainScreenUiEvent.OnItemCheckedChanged(index, !task.isChecked))
            }
        )
    }

    fun remove(index: Int, task: MainScreenItem.MainScreenTaskItem) {
        deleteTask(
            params = DeleteTaskUseCase.Params(ToDo(
                id = task.id,
                title = task.title,
                body = task.body,
                isChecked = !task.isChecked
            )),
            onSuccess = {
                sendEvent(MainScreenUiEvent.OnItemRemoved(index))
            }
        )
    }

    fun removeAll() {
        deleteAll(
            onSuccess = {
                sendEvent(
                    MainScreenUiEvent.ShowData(MainScreenViewDataMapper.buildScreen(emptyList()))
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
                    newList[event.index] = (newList[event.index] as MainScreenItem.MainScreenTaskItem).copy(isChecked = event.isChecked)
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