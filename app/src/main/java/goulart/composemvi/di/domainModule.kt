package goulart.composemvi.di

import goulart.composemvi.domain.core.ThreadContextProvider
import goulart.composemvi.domain.use_case.GetTodosUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {

    single {
        ThreadContextProvider()
    }

    factory { (scope: CoroutineScope) ->
        GetTodosUseCase(scope, get())
    }

}