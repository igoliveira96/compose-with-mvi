package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.core.UseCase
import goulart.composemvi.domain.entities.Task
import goulart.composemvi.domain.exceptions.MissingParamsException
import goulart.composemvi.domain.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope

class InsertTaskUseCase(
    scope: CoroutineScope,
    private val repository: TaskRepository
) : UseCase<Unit, InsertTaskUseCase.Params>(scope) {

    override fun run(params: Params?) = when(params) {
        null -> throw MissingParamsException()
        else -> repository.insert(
            Task.EMPTY.copy(title = params.title, body = params.body)
        )
    }

    data class Params (
        val title: String,
        val body: String,
    )

}