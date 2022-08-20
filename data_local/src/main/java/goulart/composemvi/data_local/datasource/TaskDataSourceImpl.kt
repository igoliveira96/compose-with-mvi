package goulart.composemvi.data_local.datasource

import goulart.composemvi.data.datasource.TaskDataSource
import goulart.composemvi.data_local.database.AppDataBase
import goulart.composemvi.data_local.mapper.task.TaskMapper
import goulart.composemvi.domain.entities.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskDataSourceImpl(
    private val dataBase: AppDataBase
) : TaskDataSource {

    override fun insert(task: Task) = flow {
        emit(
            dataBase.taskDao().insert(TaskMapper.fromDomain(task))
        )
    }

    override fun update(task: Task) = flow {
        emit(
            dataBase.taskDao().update(TaskMapper.fromDomain(task))
        )
    }

    override fun getAll(): Flow<List<Task>> = flow {
        emit(TaskMapper.toDomain(dataBase.taskDao().getAll()))
    }

    override fun delete(task: Task) = flow {
        emit(
            dataBase.taskDao().delete(TaskMapper.fromDomain(task))
        )
    }

    override fun delete() = flow {
        emit(
            dataBase.taskDao().delete()
        )
    }
}