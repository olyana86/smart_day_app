package com.example.smartday.utils

import androidx.compose.material3.CalendarLocale
import java.util.Calendar

fun getDateToday(): String {

    val calendar = Calendar.getInstance(CalendarLocale("RU"))
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

    val dateToday = "$year-$month-$day"

    return dateToday
}

fun getDayOfWeekFromDate(currentDate: String): String {

    val year: Int = currentDate.substringBefore('-').toInt()
    val month: Int = (currentDate.substringAfter('-')).substringBefore('-').toInt()
    val day = currentDate.substringAfterLast('-').toInt()

    val calendar = Calendar.getInstance(CalendarLocale("RU"))
    calendar.set(year, month, day)
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    return dayOfWeek.toString()
}

fun getDayOfWeekFromDateForMainScreen(currentDate: String): String {

    val dayOfWeekInt = getDayOfWeekFromDate(currentDate).toInt()

    return dayOfWeekIntToText(dayOfWeekInt)
}

fun getDateTodayForMainScreen(): String {

    val calendar = Calendar.getInstance(CalendarLocale("RU"))
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

    val dateToday = "$day ${monthIntToText(month)} $year г."
    return dateToday
}

fun convertRoomDateToScreenDate(roomDate: String): String {

    val year: Int = roomDate.substringBefore('-').toInt()
    val month: Int = (roomDate.substringAfter('-')).substringBefore('-').toInt()
    val day = roomDate.substringAfterLast('-').toInt()

    val screenDate = "$day ${monthIntToText(month)} $year г."
    return screenDate
}