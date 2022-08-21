package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.core.UseCase
import goulart.composemvi.domain.entities.Task
import goulart.composemvi.domain.exceptions.MissingParamsException
import goulart.composemvi.domain.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope

class DeleteTaskUseCase(
    scope: CoroutineScope,
    private val repository: TaskRepository
) : UseCase<Unit, DeleteTaskUseCase.Params>(scope) {

    override fun run(params: Params?) = when(params) {
        null -> throw MissingParamsException()
        else -> repository.delete(params.task)
    }

    data class Params(
        val task: Task
    )

}