package goulart.composemvi.di

import goulart.composemvi.data.datasource.TodoDataSource
import goulart.composemvi.datalocal.database.AppDataBase
import goulart.composemvi.datalocal.datasource.TodoDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {

    single {
        AppDataBase.build(androidContext())
    }

    single<TodoDataSource> { TodoDataSourceImpl(get()) }

}