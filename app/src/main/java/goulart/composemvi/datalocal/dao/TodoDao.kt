package goulart.composemvi.datalocal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import goulart.composemvi.datalocal.entity.Task

@Dao
interface TodoDao {

    @Insert
    fun insertAll(vararg tasks: Task)

    @Query("SELECT * FROM tasks")
    fun getAll(): List<Task>

    @Delete
    fun delete(task: Task)

}