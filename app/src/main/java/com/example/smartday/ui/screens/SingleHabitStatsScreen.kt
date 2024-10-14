package com.example.smartday.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.smartday.ui.components.stats.StatsHorizontalLineChart
import com.example.smartday.ui.viewmodels.SingleHabitStatsViewModel
import com.example.smartday.utils.convertRoomDateToScreenDate

@Composable
fun SingleHabitStatsScreen(
    navController: NavHostController,
    habitId: Long
) {
    val viewModel: SingleHabitStatsViewModel = hiltViewModel()

    val habit by viewModel.habit.collectAsStateWithLifecycle()
    val habitLogs = viewModel.habitLogs.collectAsStateWithLifecycle()

    if (habitLogs.value.isNotEmpty()) {
        val initialDate = habitLogs.value.first().habitLogDate
        val screenDate = convertRoomDateToScreenDate(initialDate)
        val totalNumber = habitLogs.value.size
        val totalSuccess = habitLogs.value.count { habitLog -> habitLog.habitLogValue == 1 }
        val totalFailure = totalNumber - totalSuccess
        val successPercentage = totalSuccess.toFloat() / totalNumber.coerceAtLeast(1).toFloat()
        val habitData = mutableListOf<Int>()

        for (habitLog in habitLogs.value) {
            val value = habitLog.habitLogValue
            habitData.add(value)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Статистика по привычке",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Название: ${habit.habitName}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Дата начала: $screenDate")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Успешных дней: $totalSuccess")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Неудачных дней: $totalFailure")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Результативность: ${(successPercentage * 100).toInt()}%")
            StatsHorizontalLineChart(
                modifier = Modifier.fillMaxWidth(),
                data = habitData,
                colorOfSuccess = MaterialTheme.colorScheme.error,
                colorOfFailure = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                ElevatedButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(text = "ОК")
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 36.dp, horizontal = 16.dp)
            ) {
                Text(text = "Пока нет статистики по этой привычке.")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                ElevatedButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(text = "ОК")
                }
            }
        }
    }
}