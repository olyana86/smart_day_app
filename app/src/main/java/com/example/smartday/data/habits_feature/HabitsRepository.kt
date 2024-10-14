package com.example.smartday.data.habits_feature

import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitsRepository @Inject constructor(private val habitsDao: HabitsDao) {

    suspend fun insertHabit(habit: Habit) = habitsDao.insertHabit(habit)
    suspend fun updateHabit(habit: Habit) = habitsDao.updateHabit(habit)
    suspend fun deleteHabit(habit: Habit) = habitsDao.deleteHabit(habit)
    fun getHabitById(habitId: Long): Flow<Habit> = habitsDao.getHabitById(habitId)
    fun getHabitsByDayOfWeek(dayOfWeek: String): List<Habit> {
        return habitsDao.getHabitsByDayOfWeek(dayOfWeek)
    }
    fun getAllHabits(): List<Habit> {
        return habitsDao.getAllHabits()
    }
    suspend fun updateHabitsToZero(habits: List<Habit>) =
        habitsDao.updateHabitsToZero(habits)

    suspend fun insertHabitLog(habitLog: HabitLog) = habitsDao.insertHabitLog(habitLog)
    suspend fun updateHabitLog(habitLog: HabitLog) = habitsDao.updateHabitLog(habitLog)
    fun getAllHabitLogsByHabitId(habitId: Long): List<HabitLog> {
        return habitsDao.getAllHabitLogsByHabitId(habitId)
    }

    fun getAllHabitLogs(): List<HabitLog> {
        return habitsDao.getAllHabitLogs()
    }

    private fun getHabitLogByHabitAndDate(habitId: Long, date: String): HabitLog? =
        habitsDao.getHabitLogByHabitAndDate(habitId, date)

    fun getHabitLogsTotalByDate(date: String): Int = habitsDao.getHabitLogsTotalByDate(date)


    @Transaction
    suspend fun updateHabitWithHabitLog(habit: Habit, date: String) {
        updateHabit(habit)
        var habitLog = getHabitLogByHabitAndDate(habit.habitId, date)
        if (habitLog == null) {
            habitLog = HabitLog(
                habitLogId = 0L,
                habitLogCreatorId = habit.habitId,
                habitLogValue = habit.habitIsMarked,
                habitLogDate = date
            )
            insertHabitLog(habitLog)
        } else {
            habitLog.habitLogValue = habit.habitIsMarked
            updateHabitLog(habitLog)
        }
    }

    @Transaction
    suspend fun insertHabitLogs(habits: List<Habit>, date: String) {
        val habitLogsTotal = getHabitLogsTotalByDate(date)
        if (habitLogsTotal == 0) {
            habitsDao.insertHabitLogs(habits, date)
        }
    }
}