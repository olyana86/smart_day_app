package com.example.smartday.ui.components.pickers

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar


@Composable
fun DayTaskTimePicker(currentTime: String, onChange: (String) -> Unit) {
    val mContext = LocalContext.current

    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    val mTime = remember { mutableStateOf("") }
    if (currentTime != "") {
        mTime.value = currentTime
    }

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime.value = String.format("%02d", mHour) + ":" + String.format("%02d", mMinute)
            onChange(mTime.value)
        }, mHour, mMinute, true
    )

    Row {
        Text(
            modifier = Modifier
                .padding(end = 4.dp)
                .align(Alignment.CenterVertically),
            text = mTime.value,
            style = MaterialTheme.typography.titleMedium
        )
        ElevatedButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = { mTimePickerDialog.show() }
        ) {
            Text(text = "Выбрать")
        }
    }
}