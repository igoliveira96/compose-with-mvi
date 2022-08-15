package goulart.composemvi.presentation.mapper

import goulart.composemvi.domain.entities.ToDo
import goulart.composemvi.presentation.features.main.MainScreenItem

object MainScreenViewDataMapper {

    fun buildScreen(todos: List<ToDo>): List<MainScreenItem> {
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