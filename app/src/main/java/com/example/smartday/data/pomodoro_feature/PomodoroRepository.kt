package com.example.smartday.data.pomodoro_feature

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PomodoroRepository @Inject constructor(private val pomodoroDao: PomodoroDao) {

    suspend fun insertPomodoroResult(pomodoroResult: PomodoroResult) {
        withContext(Dispatchers.IO) {
            pomodoroDao.insertPomodoroResult(pomodoroResult)
        }
    }

    suspend fun updatePomodoroResult(pomodoroResult: PomodoroResult) {
        withContext(Dispatchers.IO) {
            pomodoroDao.updatePomodoroResult(pomodoroResult)
        }
    }

    fun getPomodoroResult(): PomodoroResult {
        return pomodoroDao.getPomodoroResult()
    }
}