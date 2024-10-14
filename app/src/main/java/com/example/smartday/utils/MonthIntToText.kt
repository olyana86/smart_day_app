package com.example.smartday.utils

fun monthIntToText(monthInt: Int): String {
    var monthText = ""
    when (monthInt) {
        0 -> monthText = "января"
        1 -> monthText = "февраля"
        2 -> monthText = "марта"
        3 -> monthText = "апреля"
        4 -> monthText = "мая"
        5 -> monthText = "июня"
        6 -> monthText = "июля"
        7 -> monthText = "августа"
        8 -> monthText = "сентября"
        9 -> monthText = "октября"
        10 -> monthText = "ноября"
        11 -> monthText = "декабря"
    }
    return monthText
}
