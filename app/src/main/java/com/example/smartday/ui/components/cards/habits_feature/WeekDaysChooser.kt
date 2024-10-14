package com.example.smartday.ui.components.cards.habits_feature

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartday.ui.models.DayOfWeekModel
import com.example.smartday.ui.models.getDaysOfWeekForChooser

@Composable
fun WeekDaysChooser(
    chosenDays: String,
    onChange: (String) -> Unit
) {
    val markedDays = remember { mutableStateOf(chosenDays) }
    val weekDays = getDaysOfWeekForChooser()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        weekDays.forEach { dayOfWeek ->
            item {
                WeekDay(
                    isChecked = markedDays.value.contains(dayOfWeek.id),
                    dayOfWeekModel = dayOfWeek,
                    onCheckedChange = {
                        val dayNumber = dayOfWeek.id
                        if (it) {
                            markedDays.value += dayNumber
                        } else {
                            markedDays.value = markedDays.value.replace(dayNumber, "")
                        }
                        onChange(markedDays.value)
                    }
                )
            }
        }
    }
}

@Composable
fun WeekDay(
    isChecked: Boolean,
    dayOfWeekModel: DayOfWeekModel,
    onCheckedChange: (Boolean) -> Unit
) {
    val isChosen = remember { mutableStateOf(isChecked) }
    val color = if (isChosen.value) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.secondaryContainer.copy(0.3f)
    }

    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 1.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimaryContainer),
                shape = CircleShape
            )
            .background(color, CircleShape)
            .clickable {
                isChosen.value = !isChosen.value
                onCheckedChange(isChosen.value)
            }
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = dayOfWeekModel.title,
            style = MaterialTheme.typography.titleMedium
        )
    }
}