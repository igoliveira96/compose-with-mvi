package goulart.composemvi.domain

import goulart.composemvi.domain.core.ThreadContextProvider
import org.koin.dsl.module

val testModule = module {
    single<ThreadContextProvider> { TestContextProvider() }
}