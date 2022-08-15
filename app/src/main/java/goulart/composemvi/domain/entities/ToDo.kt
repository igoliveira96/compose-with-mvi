package goulart.composemvi.domain.entities

data class ToDo (
    val id: Long,
    val isChecked: Boolean,
    val title: String,
    val body: String,
) {

    companion object {
        val EMPTY = ToDo(
            id = 0,
            isChecked = false,
            title = "",
            body = ""
        )
    }

}