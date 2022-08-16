package goulart.composemvi.presentation.mapper

import goulart.composemvi.domain.entities.Task
import goulart.composemvi.presentation.features.main.MainScreenItem

object MainScreenViewDataMapper {

    fun buildScreen(todos: List<Task>): List<MainScreenItem> {
        val viewData = mutableListOf<MainScreenItem>()
        viewData.addAll(todos.map { todo ->
            MainScreenItem.MainScreenTaskItem(
                todo.id,
                todo.isChecked,
                todo.title,
                todo.body
            )
        })
        viewData.add(MainScreenItem.MainScreenAddButtonItem)
        return viewData
    }

}