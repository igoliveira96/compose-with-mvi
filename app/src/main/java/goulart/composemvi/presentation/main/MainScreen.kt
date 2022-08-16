package goulart.composemvi.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import goulart.composemvi.ui.components.task.Task
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = getViewModel()) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My tasks") },
                elevation = 4.dp,
                actions = {
                    IconButton(onClick = { viewModel.removeAll() }) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Remove all",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.changeAddDialogState(true) }) {
                Icon(Icons.Filled.Add, contentDescription = "Create task")
            }
        }
    ) {
        when {
            state.isLoading -> ContentWithProgress()
            state.data.isNotEmpty() -> MainScreenContent(
                state.data,
                state.isShowAddDialog,
                onItemCheckedChanged = { index, task -> viewModel.onItemCheckedChanged(index, task) },
                removeTask = { index, task -> viewModel.remove(index, task) },
                onDialogDismissClick = { viewModel.changeAddDialogState(false) },
                onDialogOkClick = { title, body -> viewModel.addNewItem(title, body) },
            )
        }
    }
}

@Composable
private fun MainScreenContent(
    data: List<MainScreenItem>,
    isShowAddDialog: Boolean,
    onItemCheckedChanged: (Int, MainScreenItem.MainScreenTaskItem) -> Unit,
    removeTask: (index: Int, task: MainScreenItem.MainScreenTaskItem) -> Unit,
    onDialogDismissClick: () -> Unit,
    onDialogOkClick: (String, String) -> Unit,
) {
    LazyColumn(
        content = {
            itemsIndexed(data) { index, item ->
                when (item) {
                    is MainScreenItem.MainScreenTaskItem -> {
                        Task(
                            task = item,
                            index,
                            onItemCheckedChanged,
                            removeTask
                        )
                    }
                    else -> {}
                }
            }
        })

    if (isShowAddDialog) {
        AddNewItemDialog(onDialogDismissClick, onDialogOkClick)
    }
}

@Composable
private fun AddNewItemDialog(
    onDialogDismissClick: () -> Unit,
    onDialogOkClick: (String, String) -> Unit,
) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    AlertDialog(onDismissRequest = { },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = "New task")

                OutlinedTextField(
                    label = { Text(text = "Title") },
                    value = title,
                    onValueChange = { newText ->
                        title = newText
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Blue,
                        disabledIndicatorColor = Color.Blue
                    )
                )

                OutlinedTextField(
                    label = { Text(text = "Body") },
                    value = body,
                    onValueChange = { newText ->
                        body = newText
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Blue,
                        disabledIndicatorColor = Color.Blue
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onDialogOkClick(title, body) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
            ) {
                Text(text = "Ok", style = TextStyle(color = Color.White, fontSize = 12.sp))
            }
        }, dismissButton = {
            Button(
                onClick = onDialogDismissClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
            ) {
                Text(text = "Cancel", style = TextStyle(color = Color.White, fontSize = 12.sp))
            }
        }
    )
}

@Composable
private fun ContentWithProgress() {
    Surface(color = Color.LightGray) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTaskPreview() {
    AddNewItemDialog({}, { _, _ -> })
}