package com.example.smartday.data.daytasks_feature

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DayTasksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDayTask(dayTask: DayTask)

    @Update
    suspend fun updateDayTask(dayTask: DayTask)

    @Delete
    suspend fun deleteDayTask(dayTask: DayTask)

    @Query("SELECT * FROM day_tasks WHERE day_task_id = :dayTaskId")
    fun getDayTaskById(dayTaskId: Long): Flow<DayTask>

    @Query("SELECT * FROM day_tasks WHERE day_task_date = :dayTaskDate")
    fun getDayTasksByDate(dayTaskDate: String): List<DayTask>

    @Query("SELECT * FROM day_tasks")
    fun getAllDayTasks(): List<DayTask>

    @Insert
    suspend fun insertAllEisenhowerTags(eisenhowerTags: List<EisenhowerTag>)

    @Query("SELECT * FROM eisenhower_tags")
    fun getAllEisenhowerTags(): List<EisenhowerTag>

}