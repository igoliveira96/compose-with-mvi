package goulart.composemvi.di

import goulart.composemvi.domain.core.ThreadContextProvider
import goulart.composemvi.domain.usecase.*
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {

    single {
        ThreadContextProvider()
    }

    factory { (scope: CoroutineScope) ->
        GetTasksUseCase(scope, get())
    }

    factory { (scope: CoroutineScope) ->
        InsertTaskUseCase(scope, get())
    }

    factory { (scope: CoroutineScope) ->
        UpdateTaskUseCase(scope, get())
    }

    factory { (scope: CoroutineScope) ->
        DeleteAllTasksUseCase(scope, get())
    }

    factory { (scope: CoroutineScope) ->
        DeleteTaskUseCase(scope, get())
    }

}