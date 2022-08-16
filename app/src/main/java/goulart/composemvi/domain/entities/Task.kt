package goulart.composemvi.domain.entities

data class Task (
    val id: Long,
    val isChecked: Boolean,
    val title: String,
    val body: String,
) {

    companion object {
        val EMPTY = Task(
            id = 0,
            isChecked = false,
            title = "",
            body = ""
        )
    }

}