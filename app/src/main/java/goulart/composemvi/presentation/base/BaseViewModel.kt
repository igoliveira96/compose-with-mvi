package goulart.composemvi.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

abstract class BaseViewModel<T: UiState, in E : UiEvent> : ViewModel(), KoinComponent {

    abstract val state: Flow<T>

}