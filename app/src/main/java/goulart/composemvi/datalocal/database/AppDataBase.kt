package goulart.composemvi.datalocal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import goulart.composemvi.datalocal.dao.TodoDao
import goulart.composemvi.datalocal.entity.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun todoDAO(): TodoDao

    companion object {
        private const val DATABASE_NAME = "todo-database-sql"

        fun build(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                DATABASE_NAME
            ).build()
        }
    }

}