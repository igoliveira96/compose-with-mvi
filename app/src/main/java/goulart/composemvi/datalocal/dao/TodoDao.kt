package goulart.composemvi.datalocal.dao

import androidx.room.*
import goulart.composemvi.datalocal.entity.Task

@Dao
interface TodoDao {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAll(): List<Task>

    @Delete
    fun delete(task: Task)

    @Query("DELETE FROM tasks")
    fun delete()

}