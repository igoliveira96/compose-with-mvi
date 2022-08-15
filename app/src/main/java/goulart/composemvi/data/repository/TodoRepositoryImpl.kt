package goulart.composemvi.data.repository

import goulart.composemvi.data.datasource.TodoDataSource
import goulart.composemvi.domain.entities.ToDo
import goulart.composemvi.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val dataSource: TodoDataSource
) : TodoRepository {

    override fun insert(todo: ToDo) = dataSource.insert(todo)

    override fun update(todo: ToDo) = dataSource.update(todo)

    override fun getTasks() = dataSource.getAll()

    override fun delete(todo: ToDo) = dataSource.delete(todo)

    override fun delete() = dataSource.delete()
}