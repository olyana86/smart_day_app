package com.example.smartday.utils

import androidx.compose.runtime.Composable
import java.util.concurrent.TimeUnit

@Composable
fun convertInputTimeToMillis(
    days: Long = 0,
    hours: Long = 0,
    minutes: Long = 0,
    seconds: Long = 0
): Long {
    return TimeUnit.DAYS.toMillis(days) + TimeUnit.HOURS.toMillis(hours) +
            TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.SECONDS.toMillis(seconds)
}