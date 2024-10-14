package com.example.smartday.data.habits_feature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "habit_id")
    val habitId: Long = 0,

    @ColumnInfo(name = "habit_name")
    val habitName: String,

    @ColumnInfo(name = "habit_week_days")
    val habitWeekDays: String = "1234567",

    @ColumnInfo(name = "habit_is_active")
    var habitIsActive: Int = 1,

    @ColumnInfo(name = "habit_block_color")
    val habitBlockColor: String,

    @ColumnInfo(name = "habit_details")
    val habitDetails: String,

    @ColumnInfo(name = "habit_is_marked")
    var habitIsMarked: Int = 0
)