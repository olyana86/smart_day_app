package com.example.smartday.data.pomodoro_feature

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PomodoroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPomodoroResult(pomodoroResult: PomodoroResult)

    @Update
    suspend fun updatePomodoroResult(pomodoroResult: PomodoroResult)

    @Query("SELECT * FROM pomodoro_results WHERE pomodoro_result_id = 1")
    fun getPomodoroResult(): PomodoroResult
}