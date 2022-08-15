package goulart.composemvi.data.datasource

import goulart.composemvi.domain.entities.ToDo
import kotlinx.coroutines.flow.Flow

interface TodoDataSource {

    fun insert(todo: ToDo): Flow<Unit>

    fun getAll(): Flow<List<ToDo>>

    fun delete(todo: ToDo): Flow<Unit>

}