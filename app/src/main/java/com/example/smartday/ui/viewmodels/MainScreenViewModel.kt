package com.example.smartday.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartday.data.daytasks_feature.DayTask
import com.example.smartday.data.daytasks_feature.DayTasksRepository
import com.example.smartday.data.habits_feature.Habit
import com.example.smartday.data.habits_feature.HabitsRepository
import com.example.smartday.utils.dayOfWeekToMondayFirstInt
import com.example.smartday.utils.getDateToday
import com.example.smartday.utils.getDayOfWeekFromDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val dayTasksRepository: DayTasksRepository,
    private val habitsRepository: HabitsRepository
) : ViewModel() {

    private val _mainHabits = MutableStateFlow(emptyList<Habit>())
    val mainHabits: StateFlow<List<Habit>> = _mainHabits

    private val _mainDayTasks = MutableStateFlow(emptyList<DayTask>())
    val mainDayTasks: StateFlow<List<DayTask>> = _mainDayTasks

    private val _habitLogsTotal = MutableStateFlow(0)
    val habitLogsTotal: StateFlow<Int> = _habitLogsTotal

    private val dateToday = getDateToday()
    private val dayOfWeek = dayOfWeekToMondayFirstInt(getDayOfWeekFromDate(dateToday).toInt())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _habitLogsTotal.emit(habitsRepository.getHabitLogsTotalByDate(dateToday))
            _mainHabits.emit(habitsRepository.getHabitsByDayOfWeek("%$dayOfWeek%"))
            _mainDayTasks.emit(dayTasksRepository.getDayTasksByDate(dateToday))
        }
    }

    fun updateHabitsToZero(habits: List<Habit>) {
        viewModelScope.launch(Dispatchers.IO) {
            habitsRepository.updateHabitsToZero(habits)
        }
    }

    fun insertHabitLogs(habits: List<Habit>, date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            habitsRepository.insertHabitLogs(habits, date)
        }
    }

    fun getDayTasksByDate(dayTaskDate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _mainDayTasks.emit(dayTasksRepository.getDayTasksByDate(dayTaskDate))
        }
    }

    fun getHabitsByDayOfWeek(dayOfWeek: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _mainHabits.emit(
                habitsRepository.getHabitsByDayOfWeek(
                    "%${
                        dayOfWeekToMondayFirstInt(
                            dayOfWeek.toInt()
                        )
                    }%"
                )
            )
        }
    }

    fun updateHabitWithHabitLog(habit: Habit, date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            habitsRepository.updateHabitWithHabitLog(habit, date)
        }
    }

    fun updateDayTask(dayTask: DayTask) {
        viewModelScope.launch(Dispatchers.IO) {
            dayTasksRepository.updateDayTask(dayTask)
        }
    }
}
