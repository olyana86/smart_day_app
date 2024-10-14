package com.example.smartday.ui.components.cards.habits_feature

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.smartday.R
import com.example.smartday.ui.models.DayOfWeekModel
import com.example.smartday.ui.models.getDaysOfWeekForChooser


@Composable
fun HabitTagInfo(
    habitInfo: String,
    modifier: Modifier,
    onClick: (Boolean) -> Unit
) {
    if (habitInfo != "") {
        Icon(
            modifier = modifier
                .size(24.dp)
                .clickable {
                    onClick(true)
                },
            imageVector = ImageVector.vectorResource(R.drawable.details_icon),
            contentDescription = "Details",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun HabitTagActivityStatus(habitIsActive: Int, modifier: Modifier) {
    val text = if (habitIsActive == 1) {
        "АКТИВНАЯ"
    } else {
        "НЕ АКТИВНАЯ"
    }
    Surface(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun HabitTagWeekDays(chosenDays: String, modifier: Modifier) {
    val weekDays = getDaysOfWeekForChooser()

    Surface(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {
            weekDays.forEach { dayOfWeek ->
                item {
                    WeekDayTag(
                        isChecked = chosenDays.contains(dayOfWeek.id),
                        dayOfWeekModel = dayOfWeek
                    )
                }
            }
        }
    }
}

@Composable
fun WeekDayTag(
    isChecked: Boolean,
    dayOfWeekModel: DayOfWeekModel
) {
    val color = if (isChecked) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }

    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 1.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.secondary), shape = CircleShape)
            .background(color, shape = CircleShape)
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = dayOfWeekModel.title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun HabitTagStats(navController: NavHostController, habitId: Long, modifier: Modifier) {
    val text = "Статистика по привычке"
    ElevatedButton(
        onClick = {
            navController.navigate("SingleHabitStats/$habitId")
        }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.single_habit_stats_icon),
            contentDescription = "Stats",
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}