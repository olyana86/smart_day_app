package com.example.smartday.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smartday.R
import com.example.smartday.ui.components.cards.habits_feature.HabitFull
import com.example.smartday.ui.dialogs.DayTaskInfoDialog
import com.example.smartday.ui.dialogs.HabitInfoDialog
import com.example.smartday.ui.theme.onPrimaryContainerLight
import com.example.smartday.ui.theme.primaryContainerLight
import com.example.smartday.ui.viewmodels.AllHabitsViewModel

@Composable
fun AllHabitsScreen(
    navController: NavHostController
) {
    val viewModel: AllHabitsViewModel = hiltViewModel()
    val habits = viewModel.habits.collectAsState()

    val shouldShowInfoDialog = remember { mutableStateOf(false) }
    val infoDialogDetailsText = remember { mutableStateOf("") }

    if (shouldShowInfoDialog.value) {
        HabitInfoDialog(infoText = infoDialogDetailsText.value, shouldShow = shouldShowInfoDialog)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
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
                text = "Мои привычки",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(
                onClick = {
                    navController.navigate("NewHabit")
                },
                content = {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(habits.value, key = { item -> item.habitId }) { item ->
                HabitFull(
                    habit = item,
                    navController = navController,
                    onInfoClick = {
                        infoDialogDetailsText.value = item.habitDetails
                        shouldShowInfoDialog.value = it
                    }
                )
            }
        }
    }
}

