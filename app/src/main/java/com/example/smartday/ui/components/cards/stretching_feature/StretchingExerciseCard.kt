package com.example.smartday.ui.components.cards.stretching_feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.smartday.ui.components.stats.NavigationProgressChart


@Composable
fun StretchingExerciseCard(
    modifier: Modifier,
    barProgress: Float,
    exerciseText: String
) {
    val progressBarColor = MaterialTheme.colorScheme.primary
    val backgroundBarColor = MaterialTheme.colorScheme.error

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        NavigationProgressChart(
            progress = barProgress,
            progressColor = progressBarColor,
            backgroundColor = backgroundBarColor
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = exerciseText,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}