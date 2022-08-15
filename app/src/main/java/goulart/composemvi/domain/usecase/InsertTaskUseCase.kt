package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.core.UseCase
import goulart.composemvi.domain.entities.ToDo
import goulart.composemvi.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope

class InsertTaskUseCase(
    scope: CoroutineScope,
    private val repository: TodoRepository
) : UseCase<Unit, InsertTaskUseCase.Params>(scope) {

    override fun run(params: Params?) = when(params) {
        null -> throw NullPointerException()
        else -> repository.insert(
            ToDo.EMPTY.copy(title = params.title, body = params.body)
        )
    }

    data class Params (
        val title: String,
        val body: String,
    )

}