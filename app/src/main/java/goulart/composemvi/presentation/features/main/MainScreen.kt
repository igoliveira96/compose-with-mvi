package goulart.composemvi.presentation.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import goulart.composemvi.ui.components.todo.ToDoItem
import goulart.composemvi.ui.theme.ComposeMVITheme
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = getViewModel()) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "To Do") },
                elevation = 4.dp,
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
                onItemCheckedChanged = { index, isChecked -> viewModel.onItemCheckedChanged(index, isChecked) },
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
    onItemCheckedChanged: (Int, Boolean) -> Unit,
    onDialogDismissClick: () -> Unit,
    onDialogOkClick: (String, String) -> Unit,
) {
    LazyColumn(
        content = {
            itemsIndexed(data) { index, item ->
                when (item) {
                    is MainScreenItem.MainScreenTodoItem -> {
                        ToDoItem(todoItem = item)
                    }
                    else -> {}
                }
            }
        })
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
fun DefaultPreview() {
    ComposeMVITheme {
        MainScreen()
    }
}