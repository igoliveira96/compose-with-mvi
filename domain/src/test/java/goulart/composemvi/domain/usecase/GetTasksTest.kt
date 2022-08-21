package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.entities.Task
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

class GetTasksTest {

    @Mock
    private lateinit var repository: TaskRepository

    private lateinit var getTasks: GetTasksUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        getTasks = GetTasksUseCase(CoroutineScope(Dispatchers.Unconfined), repository)
    }

    @After
    fun reset(){
        stopKoin()
    }

    @Test
    fun `getTasks WHEN get the tasks MUST sent a Unit result`() {
        whenever(repository.getTasks()).thenReturn(flowOf(listOf(Task.EMPTY)))

        getTasks(
            onSuccess = { result ->
                verify(repository).getTasks()
                Assert.assertEquals(listOf(Task.EMPTY), result)
            }
        )
    }

}