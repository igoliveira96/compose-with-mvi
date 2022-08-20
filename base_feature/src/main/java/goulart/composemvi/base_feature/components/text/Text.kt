package goulart.composemvi.base_feature.components.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Title(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.subtitle2
    )
}

@Composable
fun Body(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2
    )
}