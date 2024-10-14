package com.example.smartday.ui.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.withStyle
import com.example.smartday.R

@Composable
fun PomodoroInfoDialog(
    shouldShow: MutableState<Boolean>
) {
    val infoText = stringResource(id = R.string.pomodoro_long_description)
    if (shouldShow.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShow.value = false
            },
            title = {
                Text(text = "Описание методики")
            },
            text = {
                Text(text = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineBreak = LineBreak.Paragraph)) {
                        append(infoText)
                    }
                })
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