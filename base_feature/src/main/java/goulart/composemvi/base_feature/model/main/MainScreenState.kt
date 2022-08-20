package goulart.composemvi.base_feature.model.main

import goulart.composemvi.base_feature.core.UiState
import goulart.composemvi.base_feature.model.base.ScreenItem

data class MainScreenState(
    val isLoading: Boolean,
    val data: List<ScreenItem>,
    val isShowAddDialog: Boolean,
) : UiState {

    companion object {
        fun initial() = MainScreenState(
            isLoading = true,
            data = emptyList(),
            isShowAddDialog = false
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data.size: ${data.size}, isShowAddDialog: $isShowAddDialog"
    }
}
