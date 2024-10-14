package com.example.smartday.data.habits_feature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_logs")
data class HabitLog(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "habit_log_id")
    val habitLogId: Long = 0,

    @ColumnInfo(name = "habit_log_creator_id")
    val habitLogCreatorId: Long,

    @ColumnInfo(name = "habit_log_date")
    val habitLogDate: String,

    @ColumnInfo(name = "habit_log_value")
    var habitLogValue: Int = 0
)
