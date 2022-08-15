package goulart.composemvi.domain.repository

import goulart.composemvi.domain.entities.ToDo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun insert(todo: ToDo): Flow<Unit>

    fun update(todo: ToDo): Flow<Unit>

    fun getTasks(): Flow<List<ToDo>>

    fun delete(todo: ToDo): Flow<Unit>

    fun delete(): Flow<Unit>

}