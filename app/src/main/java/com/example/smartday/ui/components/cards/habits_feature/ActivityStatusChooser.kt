package com.example.smartday.ui.components.cards.habits_feature

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun ActivityStatusChooser(
    isChecked: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    val checkedState = remember { mutableStateOf(isChecked) }
    val color = if (isChecked) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onErrorContainer
    }
    val text = if (isChecked) {
        "Привычка активна"
    } else {
        "Привычка не активна"
    }

    Row {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = color
        )
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onCheckedChange(it)
            }
        )
    }
}