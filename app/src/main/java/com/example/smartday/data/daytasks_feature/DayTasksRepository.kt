package com.example.smartday.data.daytasks_feature

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DayTasksRepository @Inject constructor(private val dayTasksDao: DayTasksDao){
    suspend fun insertDayTask(dayTask: DayTask) = dayTasksDao.insertDayTask(dayTask)
    suspend fun updateDayTask(dayTask: DayTask) = dayTasksDao.updateDayTask(dayTask)
    suspend fun deleteDayTask(dayTask: DayTask) = dayTasksDao.deleteDayTask(dayTask)

    fun getDayTaskById(dayTaskId: Long): Flow<DayTask> {
        return dayTasksDao.getDayTaskById(dayTaskId)
    }

    fun getDayTasksByDate(dayTaskDate: String): List<DayTask> {
        return dayTasksDao.getDayTasksByDate(dayTaskDate)
    }

    fun getAllDayTasks(): List<DayTask> {
        return dayTasksDao.getAllDayTasks()
    }

    suspend fun insertAllEisenhowerTags(eisenhowerTags: List<EisenhowerTag>) =
        dayTasksDao.insertAllEisenhowerTags(eisenhowerTags)

    fun getAllEisenhowerTags(): List<EisenhowerTag> {
        return dayTasksDao.getAllEisenhowerTags()
    }

}