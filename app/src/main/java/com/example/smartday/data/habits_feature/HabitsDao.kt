package com.example.smartday.data.habits_feature

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)

    @Query("UPDATE habits SET habit_is_marked = 0 WHERE habit_id = :id")
    suspend fun updateHabitToZero(id: Long)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("SELECT * FROM habits WHERE habit_id = :habitId")
    fun getHabitById(habitId: Long): Flow<Habit>

    @Query("SELECT * FROM habits WHERE habit_week_days LIKE :dayOfWeek AND habit_is_active = 1")
    fun getHabitsByDayOfWeek(dayOfWeek: String): List<Habit>

    @Query("SELECT habit_id FROM habits WHERE habit_week_days LIKE :dayOfWeek AND habit_is_active = 1")
    fun getHabitsIdsByDayOfWeek(dayOfWeek: String): List<Long>

    @Query("SELECT * FROM habits")
    fun getAllHabits(): List<Habit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitLog(habitLog: HabitLog)

    @Update
    suspend fun updateHabitLog(habitLog: HabitLog)

    @Query("SELECT * FROM habit_logs WHERE habit_log_creator_id = :habitId")
    fun getAllHabitLogsByHabitId(habitId: Long): List<HabitLog>

    @Query("SELECT * FROM habit_logs")
    fun getAllHabitLogs(): List<HabitLog>

    @Query("SELECT * FROM habit_logs WHERE habit_log_creator_id = :habitId AND habit_log_date = :date")
    fun getHabitLogByHabitAndDate(habitId: Long, date: String): HabitLog?

    @Query("SELECT COUNT(*) FROM habit_logs WHERE habit_log_date = :date")
    fun getHabitLogsTotalByDate(date: String): Int

    @Transaction
    suspend fun updateHabitsToZero(habits: List<Habit>) {
        for (habit in habits) {
            updateHabitToZero(habit.habitId)
        }
    }

    @Transaction
    suspend fun insertHabitLogs(habits: List<Habit>, date: String) {
        for (habit in habits) {
            val habitLog = HabitLog(
                habitLogCreatorId = habit.habitId,
                habitLogValue = 0,
                habitLogDate = date
            )
            insertHabitLog(habitLog)
        }
    }
}