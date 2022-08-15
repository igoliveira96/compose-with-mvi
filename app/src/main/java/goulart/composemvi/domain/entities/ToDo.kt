package goulart.composemvi.domain.entities

data class ToDo (
    val id: String,
    val isChecked: Boolean,
    val title: String,
    val body: String,
) {

    companion object {
        val EMPTY = ToDo(
            id = "",
            isChecked = false,
            title = "",
            body = ""
        )
    }

}