package com.example.smartday.ui.components.pickers

import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.unit.dp
import com.example.smartday.R
import com.example.smartday.utils.dayOfWeekIntToText
import com.example.smartday.utils.getDateToday
import com.example.smartday.utils.getDateTodayForMainScreen
import com.example.smartday.utils.getDayOfWeekFromDateForMainScreen
import com.example.smartday.utils.monthIntToText
import java.util.Calendar
import java.util.Date

@Composable
fun MainDatePicker(
    modifier: Modifier,
    chosenDate: String,
    dayOfWeek: String,
    onChange: (String) -> Unit
) {
    val mContext = LocalContext.current

    val mCalendar = Calendar.getInstance(CalendarLocale("RU"))
    val mYear: Int = mCalendar.get(Calendar.YEAR)
    val mMonth: Int = mCalendar.get(Calendar.MONTH)
    val mDay: Int = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    var currentDate = getDateToday()

    currentDate = if (chosenDate == currentDate) {
        getDateTodayForMainScreen()
    } else {
        chosenDate
    }

    val dateText = remember { mutableStateOf(currentDate) }
    val dayOfWeekText = remember { mutableStateOf(dayOfWeekIntToText(dayOfWeek.toInt())) }

    val mDatePickerDialog = android.app.DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            dateText.value = "$mDayOfMonth ${monthIntToText(mMonth)} $mYear г."
            dayOfWeekText.value = getDayOfWeekFromDateForMainScreen("$mYear-$mMonth-$mDayOfMonth")
            onChange("$mYear-$mMonth-$mDayOfMonth")
        }, mYear, mMonth, mDay
    )
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Column() {
            ElevatedButton(
                onClick = { mDatePickerDialog.show() }

            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.calendar_month_icon),
                    contentDescription = "Выбор даты",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Дата:"
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                modifier = Modifier.align(Alignment.End),
                text = dateText.value,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.align(Alignment.End),
                text = dayOfWeekText.value,
                style = MaterialTheme.typography.titleMedium.plus(TextStyle(fontStyle = Italic))
            )
        }
    }
}
