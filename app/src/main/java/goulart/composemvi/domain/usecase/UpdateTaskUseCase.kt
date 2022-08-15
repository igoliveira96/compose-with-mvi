package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.core.UseCase
import goulart.composemvi.domain.entities.ToDo
import goulart.composemvi.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope

class UpdateTaskUseCase(
    scope: CoroutineScope,
    private val repository: TodoRepository
) : UseCase<Unit, UpdateTaskUseCase.Params>(scope) {

    override fun run(params: Params?) = when(params) {
        null -> throw NullPointerException()
        else -> repository.update(params.todo)
    }

    data class Params (
        val todo: ToDo
    )

}