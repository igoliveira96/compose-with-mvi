package goulart.composemvi.data.repository

import goulart.composemvi.data.datasource.TaskDataSource
import goulart.composemvi.domain.entities.Task
import goulart.composemvi.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val dataSource: TaskDataSource
) : TaskRepository {

    override fun insert(task: Task) = dataSource.insert(task)

    override fun update(task: Task) = dataSource.update(task)

    override fun getTasks() = dataSource.getAll()

    override fun delete(task: Task) = dataSource.delete(task)

    override fun delete() = dataSource.delete()
}