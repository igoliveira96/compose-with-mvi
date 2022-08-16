package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.core.UseCase
import goulart.composemvi.domain.entities.Task
import goulart.composemvi.domain.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope

class GetTasksUseCase(
    scope: CoroutineScope,
    private val repository: TaskRepository
) : UseCase<List<Task>, Unit>(scope) {

    override fun run(params: Unit?) = repository.getTasks()

}