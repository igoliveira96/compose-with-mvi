package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.core.UseCase
import goulart.composemvi.domain.entities.Task
import goulart.composemvi.domain.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope

class UpdateTaskUseCase(
    scope: CoroutineScope,
    private val repository: TaskRepository
) : UseCase<Unit, UpdateTaskUseCase.Params>(scope) {

    override fun run(params: Params?) = when(params) {
        null -> throw NullPointerException()
        else -> repository.update(params.task)
    }

    data class Params (
        val task: Task
    )

}