package com.example.smartday.ui.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smartday.ui.models.MotivationQuote
import com.example.smartday.ui.models.getRandomMotivationQuote

@Composable
fun DailyMotivationDialog(
    shouldShow: MutableState<Boolean>
) {
    val quote = getRandomMotivationQuote()
    if (shouldShow.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShow.value = false
            },
            title = {
                Text(text = "Мотивация дня")
            },
            text = {
                MotivationText(quote = quote)
            },
            confirmButton = {
                ElevatedButton(
                    onClick = {
                        shouldShow.value = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.primary,
                        disabledContentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "OK",
                        color = Color.White
                    )
                }
            }
        )
    }
}

@Composable
fun MotivationText(quote: MotivationQuote) {
    Column {
        Text(text = quote.text, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            modifier = Modifier.align(Alignment.End),
            text = quote.author,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}