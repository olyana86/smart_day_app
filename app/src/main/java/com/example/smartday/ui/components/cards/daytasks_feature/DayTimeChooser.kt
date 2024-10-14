package com.example.smartday.ui.components.cards.daytasks_feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartday.ui.components.pickers.DayTaskTimePicker


@Composable
fun DayTimeChooser(
    modifier: Modifier,
    chooserText: String,
    currentTime: String,
    onChange: (String) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = chooserText,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            DayTaskTimePicker(currentTime, onChange)
        }
    }
}