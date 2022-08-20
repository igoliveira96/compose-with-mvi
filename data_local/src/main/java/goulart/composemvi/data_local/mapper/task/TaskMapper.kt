package goulart.composemvi.data_local.mapper.task

import goulart.composemvi.data_local.entity.TaskEntity
import goulart.composemvi.data_local.mapper.base.DataLocalMapper
import goulart.composemvi.domain.entities.Task

object TaskMapper : DataLocalMapper<TaskEntity, Task> {

    override fun toDomain(local: TaskEntity) = Task(
        local.id,
        local.isChecked,
        local.title,
        local.body
    )

    override fun fromDomain(domain: Task) = TaskEntity(
        domain.id,
        domain.title,
        domain.body,
        domain.isChecked
    )

}