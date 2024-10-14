package com.example.smartday.data.pomodoro_feature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pomodoro_results")
data class PomodoroResult(
    @PrimaryKey
    @ColumnInfo(name = "pomodoro_result_id")
    val pomodoroResultId: Long = 1,

    @ColumnInfo(name = "pomodoro_total")
    val pomodoroTotal: Int = 0
)