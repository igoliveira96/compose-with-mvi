package goulart.composemvi.presentation.mapper

import goulart.composemvi.domain.entities.Task
import goulart.composemvi.presentation.main.MainScreenItem
import goulart.composemvi.presentation.mapper.base.PresentationMapper

object TaskMapper : PresentationMapper<MainScreenItem.MainScreenTaskItem, Task> {

    override fun toDomain(presentation: MainScreenItem.MainScreenTaskItem) = Task(
        id = presentation.id,
        title = presentation.title,
        body = presentation.body,
        isChecked = !presentation.isChecked
    )

    override fun fromDomain(domain: Task) = MainScreenItem.MainScreenTaskItem(
        id = domain.id,
        title = domain.title,
        body = domain.body,
        isChecked = domain.isChecked,
    )

    fun buildScreen(tasks: List<Task>): List<MainScreenItem> {
        val viewData = mutableListOf<MainScreenItem>()
        viewData.addAll(tasks.map { task -> fromDomain(task)})
        return viewData
    }

}