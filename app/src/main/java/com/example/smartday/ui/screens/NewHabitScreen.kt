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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smartday.data.habits_feature.Habit
import com.example.smartday.ui.components.cards.common.OneLineTagChooser
import com.example.smartday.ui.components.cards.common.TimeBlockColorChooser
import com.example.smartday.ui.components.cards.common.TimeBlockTagsList
import com.example.smartday.ui.components.cards.habits_feature.ActivityStatusChooser
import com.example.smartday.ui.components.cards.habits_feature.WeekDaysChooser
import com.example.smartday.ui.models.getColors
import com.example.smartday.ui.viewmodels.NewHabitViewModel
import com.example.smartday.utils.booleanToInt

@Composable
fun NewHabitScreen(
    navController: NavHostController
) {
    val viewModel: NewHabitViewModel = hiltViewModel()

    val currentHabitName = remember { mutableStateOf("") }
    val currentHabitWeekDays = remember { mutableStateOf("1234567") }
    val currentHabitBlockColorIsActive = remember { mutableStateOf(false) }
    val currentHabitBlockColor = remember { mutableStateOf("#DAE5E2") }
    val currentHabitIsActive = remember { mutableStateOf(true) }
    val currentHabitDetailsIsActive = remember { mutableStateOf(false) }
    val currentHabitDetails = remember { mutableStateOf("") }
    val timeBlockColors = getColors()

    val currentHabit = Habit(
        habitId = 0L,
        habitName = currentHabitName.value,
        habitWeekDays = currentHabitWeekDays.value,
        habitIsActive = booleanToInt(currentHabitIsActive.value),
        habitBlockColor = currentHabitBlockColor.value,
        habitDetails = currentHabitDetails.value
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = currentHabitName.value,
            onValueChange = { currentHabitName.value = it },
            label = { Text(text = "Название привычки") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "Повтор:",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.width(8.dp))
            WeekDaysChooser(
                chosenDays = currentHabitWeekDays.value,
                onChange = {
                    currentHabitWeekDays.value = it
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TimeBlockColorChooser(
            timeBlockTagIsActive = currentHabitBlockColorIsActive.value,
            onCheckedChange = {
                currentHabitBlockColorIsActive.value = it
                if (!it) {
                    currentHabitBlockColor.value = "#DAE5E2"
                }
            }
        )
        if (currentHabitBlockColorIsActive.value) {
            Box(modifier = Modifier.fillMaxWidth()) {
                TimeBlockTagsList(
                    tags = timeBlockColors,
                    currentTag = currentHabitBlockColor.value,
                    onSelect = {
                        currentHabitBlockColor.value = it
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        ActivityStatusChooser(
            isChecked = currentHabitIsActive.value,
            onCheckedChange = {
                currentHabitIsActive.value = it
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OneLineTagChooser(
            modifier = Modifier.fillMaxWidth(),
            tagsText = "Еще информация о привычке",
            tagIsChosen = currentHabitDetailsIsActive.value,
            onCheckedChange = {
                currentHabitDetailsIsActive.value = it
                if (!it) {
                    currentHabitDetails.value = ""
                }
            }
        )
        if (currentHabitDetailsIsActive.value) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = currentHabitDetails.value,
                onValueChange = { currentHabitDetails.value = it },
                label = { Text(text = "Информация о привычке") }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
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
                Text(text = "Отмена")
            }
            ElevatedButton(
                onClick = {
                    viewModel.insertNewHabit(currentHabit)
                    navController.navigate("AllHabits")
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