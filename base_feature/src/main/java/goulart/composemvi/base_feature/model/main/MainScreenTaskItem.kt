package goulart.composemvi.base_feature.model.main

import goulart.composemvi.base_feature.model.base.ScreenItem

data class MainScreenTaskItem(
    val id: Long,
    val isChecked: Boolean,
    val title: String,
    val body: String,
) : ScreenItem() {

    companion object {
        val CLEAN_THE_ROOM = MainScreenTaskItem(
            id = 0,
            isChecked = false,
            title = "Clean the room",
            body = "Clean the room on Thursday"
        )
    }

}