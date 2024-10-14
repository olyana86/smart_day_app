package com.example.smartday.ui.components.timers

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smartday.utils.convertInputTimeToMillis
import com.example.smartday.utils.formatTimeFromMillis
import kotlinx.coroutines.delay

@Composable
fun PomodoroTimer(
    onChange: (Int) -> Unit
) {
    val activeTime = convertInputTimeToMillis(minutes = 25)
    val restTime = convertInputTimeToMillis(minutes = 5)
    val timeUnitsNumber = 2

    var timeLeft by remember { mutableLongStateOf(activeTime) }
    var isRunning by remember { mutableStateOf(false) }
    var isARestTime by remember { mutableStateOf(false) }

    val borderColor: Color = if (isARestTime) {
        MaterialTheme.colorScheme.tertiary
    } else {
        MaterialTheme.colorScheme.error
    }

    LaunchedEffect(key1 = timeLeft, key2 = isRunning) {
        while (timeLeft > 0 && isRunning) {
            delay(1000)
            timeLeft -= 1000L
        }
        if (timeLeft.toInt() == 0 && !isARestTime) {
            timeLeft = restTime
            isARestTime = true
        }

        if (timeLeft.toInt() == 0 && isARestTime) {
            timeLeft = activeTime
            isARestTime = false
            onChange(1)
        }
    }

    Box(
        modifier = Modifier.border(4.dp, borderColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isARestTime) "Время отдыха" else "Время работы",
                color = borderColor,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = formatTimeFromMillis(timeLeft, timeUnitsNumber),
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                ElevatedButton(
                    onClick = {
                        isRunning = !isRunning
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(text = if (isRunning) "Пауза" else "Старт")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        timeLeft = activeTime
                        isRunning = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(text = "Заново")
                }
            }
        }
    }
}

