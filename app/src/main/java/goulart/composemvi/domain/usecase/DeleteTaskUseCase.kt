package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.core.UseCase
import goulart.composemvi.domain.entities.ToDo
import goulart.composemvi.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope

class DeleteTaskUseCase(
    scope: CoroutineScope,
    private val repository: TodoRepository
) : UseCase<Unit, DeleteTaskUseCase.Params>(scope) {

    override fun run(params: Params?) = when(params) {
        null -> throw NullPointerException()
        else -> repository.delete(params.task)
    }

    data class Params(
        val task: ToDo
    )

}