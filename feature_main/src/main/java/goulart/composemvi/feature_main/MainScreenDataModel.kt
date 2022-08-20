package goulart.composemvi.feature_main

import goulart.composemvi.base_feature.core.UiEvent
import goulart.composemvi.base_feature.model.base.ScreenItem

sealed class MainScreenUiEvent : UiEvent {
    data class ShowData(val items: List<ScreenItem>) : MainScreenUiEvent()
    data class OnChangeDialogState(val show: Boolean) : MainScreenUiEvent()
    data class OnItemCheckedChanged(val index: Int, val isChecked: Boolean) : MainScreenUiEvent()
    data class OnItemRemoved(val index: Int) : MainScreenUiEvent()
    object DismissDialog : MainScreenUiEvent()
}