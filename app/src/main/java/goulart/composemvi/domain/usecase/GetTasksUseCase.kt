package goulart.composemvi.domain.usecase

import goulart.composemvi.domain.core.UseCase
import goulart.composemvi.domain.entities.ToDo
import goulart.composemvi.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope

class GetTasksUseCase(
    scope: CoroutineScope,
    private val repository: TodoRepository
) : UseCase<List<ToDo>, Unit>(scope) {

    override fun run(params: Unit?) = repository.getTasks()

}