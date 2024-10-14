package com.example.smartday.ui.components.cards.daytasks_feature

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import com.example.smartday.data.daytasks_feature.DayTask
import com.example.smartday.utils.intToBoolean

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyDayTask(
    dayTask: DayTask,
    onCheckedChange: (Boolean) -> Unit,
    navController: NavHostController,
    onInfoClick: (Boolean) -> Unit
) {
    val dayTaskId = dayTask.dayTaskId
    val dayTaskName = if (dayTask.dayTaskName.length <= 26) {
        dayTask.dayTaskName
    } else {
        dayTask.dayTaskName.substring(0, 26)
    }
    val dayTaskDetails = dayTask.dayTaskDetails
    val dayTaskStartTime = dayTask.dayTaskStartTime
    val dayTaskEndTime = dayTask.dayTaskEndTime
    val dayTaskIsDone = intToBoolean(dayTask.dayTaskIsDone)
    val dayTaskIsAFrog = dayTask.dayTaskIsAFrog
    val dayTaskIsAPriority = dayTask.dayTaskIsAPriority
    val dayTaskEisenhowerTagId = dayTask.dayTaskEisenhowerTagId
    val dayTaskTimeBlockColor = dayTask.dayTaskTimeBlockColor
    val color = Color(dayTaskTimeBlockColor.toColorInt())

    val checked = remember { mutableStateOf(dayTaskIsDone) }

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .background(color.copy(0.5f), shape = RoundedCornerShape(8.dp))
            .border(
                2.dp,
                color = color,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    checked = checked.value,
                    onCheckedChange = {
                        checked.value = it
                        onCheckedChange(checked.value)
                    }
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = dayTaskName,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = {
                        navController.navigate("TaskEdit/$dayTaskId")
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.edit_pencil_icon),
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            FlowRow(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                DayTaskTagInfo(
                    dayTaskDetails,
                    onClick = {
                        onInfoClick(it)
                    }
                )
                DayTaskTagFrog(dayTaskIsAFrog)
                DayTaskTagStartTime(dayTaskStartTime)
                DayTaskTagEndTime(dayTaskEndTime)
                DayTaskTagPriority(dayTaskIsAPriority)
                DayTaskTagEisenhower(dayTaskEisenhowerTagId)
            }
        }
    }
}