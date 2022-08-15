package goulart.composemvi.di

import goulart.composemvi.data.repository.TodoRepositoryImpl
import goulart.composemvi.domain.repository.TodoRepository
import org.koin.dsl.module

val dataModule = module {

    single<TodoRepository> { TodoRepositoryImpl(get()) }

}