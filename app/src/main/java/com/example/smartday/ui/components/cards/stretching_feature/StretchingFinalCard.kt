package com.example.smartday.ui.components.cards.stretching_feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.smartday.R
import com.example.smartday.ui.components.stats.NavigationProgressChart
import com.example.smartday.ui.theme.secondaryLight


@Composable
fun StretchingFinalCard(
    barProgress: Float,
    onFinish: (Boolean) -> Unit
) {
    val progressBarColor = MaterialTheme.colorScheme.primary
    val backgroundBarColor = MaterialTheme.colorScheme.error
    val finalText = stringResource(id = R.string.stretching_final_exercise)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            NavigationProgressChart(
                progress = barProgress,
                progressColor = progressBarColor,
                backgroundColor = backgroundBarColor
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = finalText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            ElevatedButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    onFinish(true)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = secondaryLight,
                    disabledContentColor = secondaryLight
                )
            ) {
                Text(text = "Готово")
            }
        }
    }
}