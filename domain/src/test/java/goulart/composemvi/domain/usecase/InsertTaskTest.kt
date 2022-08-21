package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.entities.Task
import goulart.composemvi.domain.exceptions.MissingParamsException
import goulart.composemvi.domain.repository.TaskRepository
import goulart.composemvi.domain.testModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class InsertTaskTest {

    @Mock
    private lateinit var repository: TaskRepository

    private lateinit var insertTask: InsertTaskUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        insertTask = InsertTaskUseCase(CoroutineScope(Dispatchers.Unconfined), repository)
    }

    @After
    fun reset(){
        stopKoin()
    }

    @Test(expected = MissingParamsException::class)
    fun `WHEN don't receive params MUST throw MissingParamsException`() {
        insertTask.run()
    }

    @Test
    fun `insertTask WHEN has save the task MUST sent a Unit result`() {
        whenever(repository.insert(Task.EMPTY)).thenReturn(flowOf(Unit))

        insertTask(
            params = InsertTaskUseCase.Params(title = "", body = ""),
            onSuccess = { result ->
                verify(repository).insert(Task.EMPTY)
                assertEquals(Unit, result)
            }
        )
    }
}