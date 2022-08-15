package goulart.composemvi.ui.components.todo

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import goulart.composemvi.presentation.features.main.MainScreenItem.MainScreenTodoItem
import goulart.composemvi.ui.components.text.Body
import goulart.composemvi.ui.components.text.Title

@Composable
fun ToDoItem(todoItem: MainScreenTodoItem) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
    ) {
        Column(
        ) {
            Title(text = todoItem.title)
            Spacer(modifier = Modifier.height(8.dp))
            Body(text = todoItem.body)
        }
    }
}

@Preview
@Composable
fun PreviewToDoItem() {
    ToDoItem(
        MainScreenTodoItem(false, "Task title", "Make the lunch")
    )
}