package goulart.composemvi.data.datasource

import goulart.composemvi.domain.entities.Task
import kotlinx.coroutines.flow.Flow

interface TaskDataSource {

    fun insert(task: Task): Flow<Unit>

    fun update(task: Task): Flow<Unit>

    fun getAll(): Flow<List<Task>>

    fun delete(task: Task): Flow<Unit>

    fun delete(): Flow<Unit>

}