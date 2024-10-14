package com.example.smartday.data.habits_feature

import androidx.room.Embedded
import androidx.room.Relation

data class HabitWithHabitLogs(
    @Embedded val habit: Habit,
    @Relation(
        parentColumn = "habit_id",
        entityColumn = "habit_log_creator_id"
    )
    val habitLogs: List<HabitLog>
)
