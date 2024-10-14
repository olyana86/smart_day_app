package com.example.smartday.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.smartday.R
import com.example.smartday.ui.components.cards.daytasks_feature.MyDayTask
import com.example.smartday.ui.components.cards.habits_feature.HabitMain
import com.example.smartday.ui.components.pickers.MainDatePicker
import com.example.smartday.ui.components.stats.ProgressBulletChart
import com.example.smartday.ui.dialogs.DayTaskInfoDialog
import com.example.smartday.ui.viewmodels.MainScreenViewModel
import com.example.smartday.utils.booleanToInt
import com.example.smartday.utils.getDateToday
import com.example.smartday.utils.getDayOfWeekFromDate
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    navController: NavHostController
) {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val dateToday = getDateToday()
    val dayOfWeek = getDayOfWeekFromDate(dateToday)
    val currentDate = remember { mutableStateOf(dateToday) }
    val currentDayOfWeek = remember { mutableStateOf(dayOfWeek) }
    val coroutineScope = rememberCoroutineScope()

    val habitLogsTotal = viewModel.habitLogsTotal.collectAsStateWithLifecycle()
    val mainDayTasks = viewModel.mainDayTasks.collectAsStateWithLifecycle()
    val mainHabits = viewModel.mainHabits.collectAsStateWithLifecycle()

    val habitsUpdate = remember { mutableStateOf(false) }
    val dayTasksUpdate = remember { mutableStateOf(false) }
    val habitLogsInserted = remember { mutableStateOf(false) }

    LaunchedEffect(
        key1 = habitsUpdate.value, key2 = dayTasksUpdate.value,
        key3 = habitLogsTotal.value
    ) {
        if (habitsUpdate.value && !dayTasksUpdate.value) {
            habitsUpdate.value = false
        } else if (!habitsUpdate.value && dayTasksUpdate.value) {
            dayTasksUpdate.value = false
        }
        coroutineScope.launch {
            if (habitLogsTotal.value == 0 && mainHabits.value.isNotEmpty() && !habitLogsInserted.value) {
                viewModel.updateHabitsToZero(mainHabits.value)
                viewModel.insertHabitLogs(mainHabits.value, currentDate.value)
            }
            habitLogsInserted.value = true
        }
    }
    val habitBlockIsExpanded = remember { mutableStateOf(false) }
    val expandedText = remember { mutableStateOf("Раскрыть") }
    if (habitBlockIsExpanded.value) {
        expandedText.value = "Скрыть"
    } else {
        expandedText.value = "Раскрыть"
    }

    val habitsTotal = mainHabits.value.size
    val habitsDone = mainHabits.value.count { habit -> habit.habitIsMarked == 1 }
    val habitsProgress = remember(key1 = habitsTotal, key2 = habitsDone) {
        habitsDone.toFloat() / habitsTotal.coerceAtLeast(1).toFloat()
    }
    val dayTasksTotal = mainDayTasks.value.size
    val dayTasksDone = mainDayTasks.value.count { dayTask -> dayTask.dayTaskIsDone == 1 }
    val dayTasksProgress = remember(key1 = dayTasksTotal, key2 = dayTasksDone) {
        dayTasksDone.toFloat() / dayTasksTotal.coerceAtLeast(1).toFloat()
    }

    val shouldShowInfoDialog = remember { mutableStateOf(false) }
    val infoDialogDetailsText = remember { mutableStateOf("") }

    if (shouldShowInfoDialog.value) {
        DayTaskInfoDialog(infoText = infoDialogDetailsText.value, shouldShow = shouldShowInfoDialog)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                MainDatePicker(
                    modifier = Modifier,
                    chosenDate = currentDate.value,
                    dayOfWeek = currentDayOfWeek.value,
                    onChange = {
                        currentDate.value = it
                        currentDayOfWeek.value = getDayOfWeekFromDate(it)
                        viewModel.getDayTasksByDate(currentDate.value)
                        viewModel.getHabitsByDayOfWeek(currentDayOfWeek.value)
                    }
                )
            }
        }
        if (currentDate.value == dateToday) {
            if (mainHabits.value.isNotEmpty() && habitLogsTotal.value != 0) {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Icon(
                            ImageVector.vectorResource(id = R.drawable.all_habits_icon),
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically),
                            contentDescription = "AllHabits",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            text = "Привычки",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.weight(0.1f))

                        ElevatedButton(
                            onClick = {
                                habitBlockIsExpanded.value = !habitBlockIsExpanded.value
                            }
                        ) {
                            Text(text = expandedText.value)
                        }
                    }
                }
            }
            if (habitBlockIsExpanded.value) {
                Box(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row {
                        Text(text = "Мой прогресс:", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.width(16.dp))
                        ProgressBulletChart(
                            modifier = Modifier.weight(1f),
                            progress = habitsProgress,
                            progressColor = MaterialTheme.colorScheme.primaryContainer,
                            backgroundColor = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier.weight(0.4f)
                ) {
                    items(mainHabits.value, key = { item -> item.habitId }) { item ->
                        HabitMain(
                            habit = item,
                            habitsLogsInserted = habitLogsInserted.value,
                            onCheckedChange = {
                                item.habitIsMarked = booleanToInt(it)
                                viewModel.updateHabitWithHabitLog(item, currentDate.value)
                                habitsUpdate.value = true
                            },
                            navController = navController
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {

            Row(
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.day_tasks_icon),
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = "AllDayTasks",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "Дела",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .weight(0.9f)
                        .align(Alignment.CenterVertically)
                ) {
                    ProgressBulletChart(
                        modifier = Modifier,
                        progress = dayTasksProgress,
                        progressColor = MaterialTheme.colorScheme.primaryContainer,
                        backgroundColor = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.weight(0.1f))
                FloatingActionButton(onClick = {
                    navController.navigate("NewTask/${currentDate.value}")
                }) {
                    Icon(
                        Icons.Filled.Add,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = "Добавить дело"
                    )
                }
            }
        }
        if (mainDayTasks.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center

            ) {
                Column {
                    Text(
                        modifier = Modifier, text = "Добавь дела на этот день",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier, text = "для работы с ними и с привычками!=)",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
        LazyColumn(
            modifier = Modifier.weight(0.6f)
        ) {
            items(mainDayTasks.value, key = { item -> item.dayTaskId }) { item ->
                MyDayTask(
                    dayTask = item,
                    onCheckedChange = {
                        item.dayTaskIsDone = booleanToInt(it)
                        viewModel.updateDayTask(item)
                        dayTasksUpdate.value = true
                    },
                    navController = navController,
                    onInfoClick = {
                        infoDialogDetailsText.value = item.dayTaskDetails
                        shouldShowInfoDialog.value = it
                    }
                )
            }
        }
    }
}

