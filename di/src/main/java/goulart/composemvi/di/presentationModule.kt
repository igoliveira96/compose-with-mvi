package goulart.composemvi.di

import goulart.composemvi.feature_main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { MainViewModel() }

}