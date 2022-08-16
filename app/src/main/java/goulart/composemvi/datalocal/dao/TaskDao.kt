package goulart.composemvi.datalocal.dao

import androidx.room.*
import goulart.composemvi.datalocal.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert
    fun insert(task: TaskEntity)

    @Update
    fun update(task: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun getAll(): List<TaskEntity>

    @Delete
    fun delete(task: TaskEntity)

    @Query("DELETE FROM tasks")
    fun delete()

}