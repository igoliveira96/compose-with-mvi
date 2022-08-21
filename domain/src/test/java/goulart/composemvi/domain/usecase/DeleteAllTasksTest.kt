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

class DeleteAllTasksTest {

    @Mock
    private lateinit var repository: TaskRepository

    private lateinit var deleteAll: DeleteAllTasksUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        deleteAll = DeleteAllTasksUseCase(CoroutineScope(Dispatchers.Unconfined), repository)
    }

    @After
    fun reset(){
        stopKoin()
    }

    @Test
    fun `deleteAll WHEN has delete all tasks MUST sent a Unit result`() {
        whenever(repository.delete()).thenReturn(flowOf(Unit))

        deleteAll(
            onSuccess = { result ->
                verify(repository).delete()
                Assert.assertEquals(Unit, result)
            }
        )
    }

}