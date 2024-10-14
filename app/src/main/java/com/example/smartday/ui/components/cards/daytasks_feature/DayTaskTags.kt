package com.example.smartday.ui.components.cards.daytasks_feature

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.smartday.R


@Composable
fun DayTaskTagInfo(
    dayTaskHasInfo: String,
    onClick: (Boolean) -> Unit
) {
    if (dayTaskHasInfo != "") {
        Icon(
            modifier = Modifier
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
fun DayTaskTagFrog(dayTaskIsAFrog: Int) {
    if (dayTaskIsAFrog == 1) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.frog_tag_icon),
            contentDescription = "Лягушка"
        )
    }
}

@Composable
fun DayTaskTagPriority(dayTaskIsAPriority: Int) {
    if (dayTaskIsAPriority == 1) {
        Surface(
            shape = CircleShape,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                text = "ПРИОРИТЕТ",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun DayTaskTagEisenhower(dayTaskEisenhowerTagId: Int) {
    if (dayTaskEisenhowerTagId != 0) {
        var eisenhowerText = ""
        when (dayTaskEisenhowerTagId) {
            1 -> eisenhowerText = "Важное, срочное"
            2 -> eisenhowerText = "Важное, не срочное"
            3 -> eisenhowerText = "Срочное, не важное"
            4 -> eisenhowerText = "Не срочное, не важное"
        }
        Surface(
            shape = CircleShape,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                text = eisenhowerText,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun DayTaskTagStartTime(dayTaskStartTime: String) {
    if (dayTaskStartTime != "") {
        Surface(
            shape = CircleShape,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                text = "c $dayTaskStartTime",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun DayTaskTagEndTime(dayTaskEndTime: String) {
    if (dayTaskEndTime != "") {
        Surface(
            shape = CircleShape,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                text = "до $dayTaskEndTime",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
