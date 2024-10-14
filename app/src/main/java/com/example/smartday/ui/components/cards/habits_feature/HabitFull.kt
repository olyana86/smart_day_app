package com.example.smartday.ui.components.cards.habits_feature

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.smartday.data.habits_feature.Habit

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HabitFull(
    habit: Habit,
    navController: NavHostController,
    onInfoClick: (Boolean) -> Unit
) {
    val color = Color(habit.habitBlockColor.toColorInt())

    Box(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .background(color.copy(0.8f), shape = RoundedCornerShape(8.dp))
            .border(
                2.dp,
                color = color,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            OneLineHabitTitle(
                title = habit.habitName,
                habitId = habit.habitId,
                navController = navController
            )
            Spacer(modifier = Modifier.height(4.dp))
            FlowRow(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                HabitTagWeekDays(
                    chosenDays = habit.habitWeekDays,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                HabitTagActivityStatus(
                    habitIsActive = habit.habitIsActive,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                HabitTagInfo(
                    habitInfo = habit.habitDetails,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = {
                        onInfoClick(it)
                    }
                )
                HabitTagStats(
                    navController = navController,
                    habitId = habit.habitId,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}