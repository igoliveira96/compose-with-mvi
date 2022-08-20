package goulart.composemvi.di

import goulart.composemvi.data.datasource.TaskDataSource
import goulart.composemvi.data_local.database.AppDataBase
import goulart.composemvi.data_local.datasource.TaskDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {

    single {
        AppDataBase.build(androidContext())
    }

    single<TaskDataSource> { TaskDataSourceImpl(get()) }

}