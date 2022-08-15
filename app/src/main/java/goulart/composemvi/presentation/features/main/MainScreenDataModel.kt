package goulart.composemvi.presentation.features.main

import goulart.composemvi.base.UiEvent
import goulart.composemvi.base.UiState

sealed class MainScreenUiEvent : UiEvent {
    data class ShowData(val items: List<MainScreenItem>) : MainScreenUiEvent()
    data class OnChangeDialogState(val show: Boolean) : MainScreenUiEvent()
    data class OnItemCheckedChanged(val index: Int, val isChecked: Boolean) : MainScreenUiEvent()
    data class OnItemRemoved(val index: Int) : MainScreenUiEvent()
    object DismissDialog : MainScreenUiEvent()
}

data class MainScreenState(
    val isLoading: Boolean,
    val data: List<MainScreenItem>,
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

sealed class MainScreenItem {

    object MainScreenAddButtonItem : MainScreenItem()

    data class MainScreenTaskItem(
        val id: Long,
        val isChecked: Boolean,
        val title: String,
        val body: String,
    ) : MainScreenItem() {

        companion object {

            val CLEAN_THE_ROOM = MainScreenTaskItem(
                id = 0,
                isChecked = false,
                title = "Clean the room",
                body = "Clean the room on Thursday"
            )

        }

    }

}