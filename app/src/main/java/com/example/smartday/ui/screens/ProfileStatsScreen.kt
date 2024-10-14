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
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.smartday.ui.components.stats.ProfileProgressPieChart
import com.example.smartday.ui.components.stats.ProgressBulletChart
import com.example.smartday.ui.models.getProfileRank
import com.example.smartday.ui.viewmodels.ProfileStatsViewModel

@Composable
fun ProfileStatsScreen(navController: NavHostController) {

    val viewModel: ProfileStatsViewModel = hiltViewModel()

    val habits = viewModel.habits.collectAsStateWithLifecycle()
    val dayTasks = viewModel.dayTasks.collectAsStateWithLifecycle()
    val habitLogs = viewModel.habitLogs.collectAsStateWithLifecycle()

    if (habits.value.isNotEmpty() || dayTasks.value.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            val dayTasksTotal = dayTasks.value.size
            val dayTasksSuccess = dayTasks.value.count { dayTask -> dayTask.dayTaskIsDone == 1 }
            val dayTasksSmart = dayTasksSuccess.toFloat() / dayTasksTotal.coerceAtLeast(1).toFloat()

            val habitsTotal = habits.value.size
            val habitsActive = habits.value.count { habit -> habit.habitIsActive == 1 }

            val habitLogsTotal = habitLogs.value.size
            val habitLogsSuccess = habitLogs.value.count { habitLog -> habitLog.habitLogValue == 1 }
            val habitsSmart = habitLogsSuccess.toFloat() / habitLogsTotal.coerceAtLeast(1).toFloat()

            val lifeTotal = dayTasksTotal + habitLogsTotal
            val lifeSuccess = dayTasksSuccess + habitLogsSuccess
            val lifeSmart =
                ((lifeSuccess.toFloat() / lifeTotal.coerceAtLeast(1).toFloat()) * 100).toInt()
            val rank = getProfileRank(lifeSmart)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Статистика профиля",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Было запланировано дел: $dayTasksTotal")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Всего выполнено дел: $dayTasksSuccess")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Результативность по делам: ${(dayTasksSmart * 100).toInt()}%")
                Spacer(modifier = Modifier.height(4.dp))
                ProgressBulletChart(
                    modifier = Modifier,
                    progress = dayTasksSmart,
                    progressColor = MaterialTheme.colorScheme.primaryContainer,
                    backgroundColor = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Всего привычек: $habitsTotal")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Активно сейчас: $habitsActive")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Средняя эффективность: ${(habitsSmart * 100).toInt()}%")
                Spacer(modifier = Modifier.height(4.dp))
                ProgressBulletChart(
                    modifier = Modifier,
                    progress = habitsSmart,
                    progressColor = MaterialTheme.colorScheme.primaryContainer,
                    backgroundColor = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "ОБЩАЯ ЭФФЕКТИВНОСТЬ",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Card(
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Row {
                        Box(
                            modifier = Modifier
                                .weight(0.4f)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            ProfileProgressPieChart(
                                totalData = lifeTotal,
                                progressData = lifeSuccess,
                                pieSize = 100.dp,
                                progressColor = MaterialTheme.colorScheme.error,
                                backgroundColor = MaterialTheme.colorScheme.primary,
                                pieValueColor = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Box(
                            modifier = Modifier.weight(0.6f),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    text = "МОЙ ТЕКУЩИЙ СТАТУС",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = rank,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
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
                Text(text = "Пока нет статистики по делам и привычкам.")
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