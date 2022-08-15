package goulart.composemvi.domain.repository

import goulart.composemvi.domain.entities.ToDo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun insert(todo: ToDo): Flow<Unit>

    fun getTodos(): Flow<List<ToDo>>

    fun delete(todo: ToDo): Flow<Unit>

}