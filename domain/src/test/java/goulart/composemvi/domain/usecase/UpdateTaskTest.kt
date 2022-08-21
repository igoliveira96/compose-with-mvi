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

class UpdateTaskTest {

    @Mock
    private lateinit var repository: TaskRepository

    private lateinit var updateTask: UpdateTaskUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        updateTask = UpdateTaskUseCase(CoroutineScope(Dispatchers.Unconfined), repository)
    }

    @After
    fun reset(){
        stopKoin()
    }

    @Test(expected = MissingParamsException::class)
    fun `WHEN don't receive params MUST throw MissingParamsException`() {
        updateTask.run()
    }

    @Test
    fun `updateTask WHEN has update the task MUST sent a Unit result`() {
        whenever(repository.update(Task.EMPTY)).thenReturn(flowOf(Unit))

        updateTask(
            params = UpdateTaskUseCase.Params(Task.EMPTY),
            onSuccess = { result ->
                verify(repository).update(Task.EMPTY)
                Assert.assertEquals(Unit, result)
            }
        )
    }

}