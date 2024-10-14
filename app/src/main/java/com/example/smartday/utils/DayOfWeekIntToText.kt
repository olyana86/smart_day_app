package com.example.smartday.utils

fun dayOfWeekIntToText(dayOfWeekInt: Int): String {
    var dayOfWeekText = ""

    when (dayOfWeekInt) {
        1 -> dayOfWeekText = "воскресенье"
        2 -> dayOfWeekText = "понедельник"
        3 -> dayOfWeekText = "вторник"
        4 -> dayOfWeekText = "среда"
        5 -> dayOfWeekText = "четверг"
        6 -> dayOfWeekText = "пятница"
        7 -> dayOfWeekText = "суббота"
    }
    return dayOfWeekText
}

fun dayOfWeekToMondayFirstInt(dayOfWeekInt: Int): String {
    var newDayOfWeek = ""

    when (dayOfWeekInt) {
        1 -> newDayOfWeek = "7"
        2 -> newDayOfWeek = "1"
        3 -> newDayOfWeek = "2"
        4 -> newDayOfWeek = "3"
        5 -> newDayOfWeek = "4"
        6 -> newDayOfWeek = "5"
        7 -> newDayOfWeek = "6"
    }
    return newDayOfWeek
}