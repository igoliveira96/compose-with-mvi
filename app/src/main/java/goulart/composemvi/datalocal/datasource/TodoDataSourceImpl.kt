package goulart.composemvi.datalocal.datasource

import goulart.composemvi.data.datasource.TodoDataSource
import goulart.composemvi.datalocal.database.AppDataBase
import goulart.composemvi.datalocal.mapper.todo.TodoMapper
import goulart.composemvi.domain.entities.ToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoDataSourceImpl(
    private val dataBase: AppDataBase
) : TodoDataSource {

    override fun insert(todo: ToDo) = flow {
        emit(
            dataBase.todoDAO().insert(TodoMapper.fromDomain(todo))
        )
    }

    override fun update(todo: ToDo) = flow {
        emit(
            dataBase.todoDAO().update(TodoMapper.fromDomain(todo))
        )
    }

    override fun getAll(): Flow<List<ToDo>> = flow {
        emit(TodoMapper.toDomain(dataBase.todoDAO().getAll()))
    }

    override fun delete(todo: ToDo) = flow {
        emit(
            dataBase.todoDAO().delete(TodoMapper.fromDomain(todo))
        )
    }

    override fun delete() = flow {
        emit(
            dataBase.todoDAO().delete()
        )
    }
}