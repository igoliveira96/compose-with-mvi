package goulart.composemvi.domain.repository

import goulart.composemvi.domain.entities.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun insert(task: Task): Flow<Unit>

    fun update(task: Task): Flow<Unit>

    fun getTasks(): Flow<List<Task>>

    fun delete(task: Task): Flow<Unit>

    fun delete(): Flow<Unit>

}