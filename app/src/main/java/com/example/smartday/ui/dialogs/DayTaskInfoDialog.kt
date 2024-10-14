package com.example.smartday.ui.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color


@Composable
fun DayTaskInfoDialog(
    infoText: String,
    shouldShow: MutableState<Boolean>
) {
    if (shouldShow.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShow.value = false
            },
            title = {
                Text(text = "Информация о деле")
            },
            text = {
                Text(text = infoText)
            },
            confirmButton = {
                Button(
                    onClick = {
                        shouldShow.value = false
                    }) {
                    Text(
                        text = "OK",
                        color = Color.White
                    )
                }
            }
        )
    }
}