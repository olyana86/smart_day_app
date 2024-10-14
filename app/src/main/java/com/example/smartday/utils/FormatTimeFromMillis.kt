package com.example.smartday.utils

import java.util.concurrent.TimeUnit

fun formatTimeFromMillis(timeMi: Long, timeUnitsNumber: Int): String {
    val days = TimeUnit.MILLISECONDS.toDays(timeMi)
    val hours = TimeUnit.MILLISECONDS.toHours(timeMi) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeMi) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeMi) % 60

    var time = ""

    when (timeUnitsNumber) {
        4 -> time = String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds)
        3 -> time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        2 -> time = String.format("%02d:%02d", minutes, seconds)
        1 -> time = String.format("%02d", seconds)
    }
    return time
}

fun formatTimeFromSeconds(timeSeconds: Long): String {
    val minutes = TimeUnit.SECONDS.toMinutes(timeSeconds)
    val seconds = TimeUnit.SECONDS.toSeconds(timeSeconds) % 60
    val time = String.format("%02d:%02d", minutes, seconds)
    return time
}