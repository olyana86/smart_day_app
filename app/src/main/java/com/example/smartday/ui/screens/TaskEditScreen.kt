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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.smartday.R
import com.example.smartday.data.database.SmartDayDatabase.Companion.PrepopulateData.eisenhowerTags
import com.example.smartday.data.daytasks_feature.DayTask
import com.example.smartday.ui.components.cards.common.OneLineTagChooser
import com.example.smartday.ui.components.cards.common.TimeBlockColorChooser
import com.example.smartday.ui.components.cards.common.TimeBlockTagsList
import com.example.smartday.ui.components.cards.daytasks_feature.DayTimeChooser
import com.example.smartday.ui.components.cards.daytasks_feature.EisenhowerTagChooser
import com.example.smartday.ui.components.cards.daytasks_feature.EisenhowerTagsList
import com.example.smartday.ui.models.getColors
import com.example.smartday.ui.viewmodels.TaskEditViewModel
import com.example.smartday.utils.booleanToInt
import com.example.smartday.utils.intToBoolean

@Composable
fun TaskEditScreen(
    navController: NavHostController,
    dayTaskId: Long
) {
    val viewModel: TaskEditViewModel = hiltViewModel()
    val dayTask by viewModel.dayTask.collectAsStateWithLifecycle()

    if (dayTask.dayTaskId != 0L) {
        val currentDayTaskName = remember { mutableStateOf(dayTask.dayTaskName) }
        val currentDayTaskDetailsIsActive =
            remember { mutableStateOf(dayTask.dayTaskDetails != "") }
        val currentDayTaskDetails = remember { mutableStateOf(dayTask.dayTaskDetails) }
        val currentDayTaskDate = remember { mutableStateOf(dayTask.dayTaskDate) }
        val currentDayTaskStartTime = remember { mutableStateOf(dayTask.dayTaskStartTime) }
        val currentDayTaskEndTime = remember { mutableStateOf(dayTask.dayTaskEndTime) }
        val currentDayTaskIsDone = remember { mutableStateOf(intToBoolean(dayTask.dayTaskIsDone)) }
        val currentDayTaskIsAFrog =
            remember { mutableStateOf(intToBoolean(dayTask.dayTaskIsAFrog)) }
        val currentDayTaskIsAPriority =
            remember { mutableStateOf(intToBoolean(dayTask.dayTaskIsAPriority)) }
        val currentDayTaskEisenhowerTagIsActive =
            remember { mutableStateOf(dayTask.dayTaskEisenhowerTagId != 0) }
        val currentDayTaskEisenhowerTagId =
            remember { mutableIntStateOf(dayTask.dayTaskEisenhowerTagId) }
        val currentDayTaskTimeBlockColorIsActive =
            remember { mutableStateOf(dayTask.dayTaskTimeBlockColor != "#DAE5E2") }
        val currentDayTaskTimeBlockColor =
            remember { mutableStateOf(dayTask.dayTaskTimeBlockColor) }
        val timeBlockColors = getColors()

        val currentDayTask = DayTask(
            dayTaskId = dayTaskId,
            dayTaskName = currentDayTaskName.value,
            dayTaskDetails = currentDayTaskDetails.value,
            dayTaskDate = currentDayTaskDate.value,
            dayTaskStartTime = currentDayTaskStartTime.value,
            dayTaskEndTime = currentDayTaskEndTime.value,
            dayTaskIsDone = booleanToInt(currentDayTaskIsDone.value),
            dayTaskIsAFrog = booleanToInt(currentDayTaskIsAFrog.value),
            dayTaskIsAPriority = booleanToInt(currentDayTaskIsAPriority.value),
            dayTaskEisenhowerTagId = currentDayTaskEisenhowerTagId.intValue,
            dayTaskTimeBlockColor = currentDayTaskTimeBlockColor.value
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = currentDayTaskName.value,
                onValueChange = { currentDayTaskName.value = it },
                label = { Text(text = "Название дела") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                DayTimeChooser(
                    modifier = Modifier.weight(0.5f),
                    chooserText = "Время начала:",
                    currentTime = currentDayTaskStartTime.value,
                    onChange = {
                        currentDayTaskStartTime.value = it
                    }
                )
                DayTimeChooser(
                    modifier = Modifier.weight(0.5f),
                    chooserText = "Время окончания:",
                    currentTime = currentDayTaskEndTime.value,
                    onChange = {
                        currentDayTaskEndTime.value = it
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Выбор меток для дела",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    IconButton(
                        onClick = {
                            navController.navigate("AppInfo")
                        }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.marks_info_icon),
                            contentDescription = "Marks Info",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OneLineTagChooser(
                    modifier = Modifier.weight(0.5f),
                    tagsText = "Лягушка",
                    tagIsChosen = currentDayTaskIsAFrog.value,
                    onCheckedChange = {
                        currentDayTaskIsAFrog.value = it
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                OneLineTagChooser(
                    modifier = Modifier.weight(0.5f),
                    tagsText = "Приоритет",
                    tagIsChosen = currentDayTaskIsAPriority.value,
                    onCheckedChange = {
                        currentDayTaskIsAPriority.value = it
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            EisenhowerTagChooser(
                eisenhowerTagIsActive = currentDayTaskEisenhowerTagIsActive.value,
                onCheckedChange = {
                    currentDayTaskEisenhowerTagIsActive.value = it
                    if (!it) {
                        currentDayTaskEisenhowerTagId.intValue = 0
                    }
                }
            )
            if (currentDayTaskEisenhowerTagIsActive.value) {
                if (currentDayTaskEisenhowerTagId.intValue == 0) {
                    currentDayTaskEisenhowerTagId.intValue = 1
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    EisenhowerTagsList(
                        tags = eisenhowerTags,
                        currentTag = currentDayTaskEisenhowerTagId.intValue,
                        onSelect = {
                            currentDayTaskEisenhowerTagId.intValue = it
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            TimeBlockColorChooser(
                timeBlockTagIsActive = currentDayTaskTimeBlockColorIsActive.value,
                onCheckedChange = {
                    currentDayTaskTimeBlockColorIsActive.value = it
                    if (!it) {
                        currentDayTaskTimeBlockColor.value = "#DAE5E2"
                    }
                }
            )
            if (currentDayTaskTimeBlockColorIsActive.value) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    TimeBlockTagsList(
                        tags = timeBlockColors,
                        currentTag = currentDayTaskTimeBlockColor.value,
                        onSelect = {
                            currentDayTaskTimeBlockColor.value = it
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OneLineTagChooser(
                modifier = Modifier.fillMaxWidth(),
                tagsText = "Добавить информацию о деле",
                tagIsChosen = currentDayTaskDetailsIsActive.value,
                onCheckedChange = {
                    currentDayTaskDetailsIsActive.value = it
                    if (!it) {
                        currentDayTaskDetails.value = ""
                    }
                }
            )
            if (currentDayTaskDetailsIsActive.value) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = currentDayTaskDetails.value,
                    onValueChange = { currentDayTaskDetails.value = it },
                    label = { Text(text = "Информация о деле") }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ElevatedButton(
                    onClick = {
                        viewModel.deleteDayTask(currentDayTask)
                        navController.navigate("Main")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(text = "Удалить дело")
                }
                ElevatedButton(
                    onClick = {
                        viewModel.updateDayTask(currentDayTask)
                        navController.navigate("Main")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(text = "Сохранить")
                }
            }
        }
    }
}