package goulart.composemvi.data_local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import goulart.composemvi.data_local.dao.TaskDao
import goulart.composemvi.data_local.database.AppDataBase
import goulart.composemvi.data_local.entity.TaskEntity
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class TaskDaoTest {

    private lateinit var taskDao: TaskDao
    private lateinit var db: AppDataBase

    @Before
    fun create() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java).build()
        taskDao = db.taskDao()
    }

    @After
    fun cleanup() {
        db.close()
    }

    @Test
    fun insertTask() {
        // GIVEN
        val task = TaskEntity(1, "Title", "Body", false)

        // WHEN
        taskDao.insert(task)

        // THEN
        assertTrue(taskDao.getAll().contains(task))
    }

    @Test
    fun updateTask() {
        // GIVEN
        val task = TaskEntity(1, "Title", "Body", false)
        taskDao.insert(task)
        val updatedTask = task.copy(isChecked = true)

        // WHEN
        taskDao.update(updatedTask)

        // THEN
        assertTrue(taskDao.getAll().contains(updatedTask))
    }

    @Test
    fun deleteTask() {
        // GIVEN
        val task = TaskEntity(1, "Title", "Body", false)
        taskDao.insert(task)

        // WHEN
        taskDao.delete(task)

        // THEN
        assertFalse(taskDao.getAll().contains(task))
    }

    @Test
    fun deleteAll() {
        // GIVEN
        val task = TaskEntity(1, "Title", "Body", false)
        taskDao.insert(task)

        // WHEN
        taskDao.delete()

        // THEN
        assertTrue(taskDao.getAll().isEmpty())
    }

}