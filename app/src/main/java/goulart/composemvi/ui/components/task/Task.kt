package goulart.composemvi.ui.components.task

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import goulart.composemvi.presentation.main.MainScreenItem.MainScreenTaskItem
import goulart.composemvi.ui.components.text.Body
import goulart.composemvi.ui.components.text.Title

@Composable
fun Task(
    task: MainScreenTaskItem,
    index: Int,
    onItemCheckedChanged: (Int, MainScreenTaskItem) -> Unit,
    remove: (index: Int, task: MainScreenTaskItem) -> Unit)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            Modifier.fillMaxWidth()
        ) {
            Checkbox(
                colors = CheckboxDefaults.colors(Color.Blue),
                checked = task.isChecked,
                onCheckedChange = {
                    onItemCheckedChanged(index, task)
                }
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Title(text = task.title)
                Spacer(modifier = Modifier.height(8.dp))
                Body(text = task.body)
            }
            IconButton(onClick = { remove(index, task) }) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "Remove task",
                    tint = Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
fun TaskPreview() {
    Task(
        task = MainScreenTaskItem.CLEAN_THE_ROOM,
        index = 0,
        onItemCheckedChanged = { _, _ -> },
        remove = { _, _ -> },
    )
}