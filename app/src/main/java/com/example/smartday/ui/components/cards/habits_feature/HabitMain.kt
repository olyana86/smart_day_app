package com.example.smartday.ui.components.cards.habits_feature

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.smartday.R
import com.example.smartday.data.habits_feature.Habit
import com.example.smartday.utils.intToBoolean

@Composable
fun HabitMain(
    habit: Habit,
    habitsLogsInserted: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    navController: NavHostController
) {
    val title = if (habit.habitName.length <= 26) {
        habit.habitName
    } else {
        habit.habitName.substring(0, 26)
    }

    var isChecked = false
    if (habitsLogsInserted) {
        isChecked = intToBoolean(habit.habitIsMarked)
    }

    val habitId = habit.habitId
    val color = Color(habit.habitBlockColor.toColorInt())

    val isMarked = remember { mutableStateOf(isChecked) }

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .background(color.copy(0.7f), shape = RoundedCornerShape(8.dp))
            .border(
                2.dp,
                color = color,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                modifier = Modifier.align(Alignment.CenterVertically),
                checked = isMarked.value,
                onCheckedChange = {
                    isMarked.value = !isMarked.value
                    onCheckedChange(isMarked.value)
                }
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = {
                    navController.navigate("HabitEdit/$habitId")
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.edit_pencil_icon),
                    contentDescription = "Edit",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}