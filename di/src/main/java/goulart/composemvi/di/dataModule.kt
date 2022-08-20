package goulart.composemvi.di

import goulart.composemvi.data.repository.TaskRepositoryImpl
import goulart.composemvi.domain.repository.TaskRepository
import org.koin.dsl.module

val dataModule = module {

    single<TaskRepository> { TaskRepositoryImpl(get()) }

}