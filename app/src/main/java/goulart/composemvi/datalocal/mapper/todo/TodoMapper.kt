package goulart.composemvi.datalocal.mapper.todo

import goulart.composemvi.datalocal.entity.Task
import goulart.composemvi.datalocal.mapper.base.DataLocalMapper
import goulart.composemvi.domain.entities.ToDo

object TodoMapper : DataLocalMapper<Task, ToDo> {

    override fun toDomain(local: Task) = ToDo(
        local.id,
        local.isChecked,
        local.title,
        local.body
    )

    override fun fromDomain(domain: ToDo) = Task(
        domain.id,
        domain.title,
        domain.body,
        domain.isChecked
    )

}