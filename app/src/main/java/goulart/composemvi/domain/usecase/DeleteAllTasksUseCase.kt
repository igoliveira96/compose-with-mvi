package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.core.UseCase
import goulart.composemvi.domain.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope

class DeleteAllTasksUseCase(
    scope: CoroutineScope,
    private val repository: TaskRepository
) : UseCase<Unit, Unit>(scope) {

    override fun run(params: Unit?) = repository.delete()

}