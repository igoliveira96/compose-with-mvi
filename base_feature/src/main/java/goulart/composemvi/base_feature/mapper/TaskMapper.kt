package goulart.composemvi.base_feature.mapper

import goulart.composemvi.base_feature.model.base.ScreenItem
import goulart.composemvi.base_feature.model.main.MainScreenTaskItem
import goulart.composemvi.domain.entities.Task
import goulart.composemvi.base_feature.mapper.base.PresentationMapper

object TaskMapper : PresentationMapper<MainScreenTaskItem, Task> {

    override fun toDomain(presentation: MainScreenTaskItem) = Task(
        id = presentation.id,
        title = presentation.title,
        body = presentation.body,
        isChecked = !presentation.isChecked
    )

    override fun fromDomain(domain: Task) = MainScreenTaskItem(
        id = domain.id,
        title = domain.title,
        body = domain.body,
        isChecked = domain.isChecked,
    )

    fun buildScreen(tasks: List<Task>): List<ScreenItem> {
        val viewData = mutableListOf<ScreenItem>()
        viewData.addAll(tasks.map { task -> fromDomain(task) })
        return viewData
    }

}