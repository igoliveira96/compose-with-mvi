package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.entities.Task
import goulart.composemvi.domain.exceptions.MissingParamsException
import goulart.composemvi.domain.repository.TaskRepository
import goulart.composemvi.domain.testModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DeleteTaskTest {

    @Mock
    private lateinit var repository: TaskRepository

    private lateinit var deleteTask: DeleteTaskUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        deleteTask = DeleteTaskUseCase(CoroutineScope(Dispatchers.Unconfined), repository)
    }

    @After
    fun reset(){
        stopKoin()
    }

    @Test(expected = MissingParamsException::class)
    fun `WHEN don't receive params MUST throw MissingParamsException`() {
        deleteTask.run()
    }

    @Test
    fun `deleteTask WHEN has delete the task MUST sent a Unit result`() {
        whenever(repository.delete(Task.EMPTY)).thenReturn(flowOf(Unit))

        deleteTask(
            params = DeleteTaskUseCase.Params(Task.EMPTY),
            onSuccess = { result ->
                verify(repository).delete(Task.EMPTY)
                Assert.assertEquals(Unit, result)
            }
        )
    }

}