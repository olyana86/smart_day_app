package com.example.smartday.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartday.data.daytasks_feature.DayTask
import com.example.smartday.data.daytasks_feature.DayTasksRepository
import com.example.smartday.data.habits_feature.Habit
import com.example.smartday.data.habits_feature.HabitLog
import com.example.smartday.data.habits_feature.HabitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileStatsViewModel @Inject constructor(
    private val habitsRepository: HabitsRepository,
    private val dayTasksRepository: DayTasksRepository
) : ViewModel() {

    private val _habits = MutableStateFlow(emptyList<Habit>())
    val habits: StateFlow<List<Habit>> = _habits

    private val _dayTasks = MutableStateFlow(emptyList<DayTask>())
    val dayTasks: StateFlow<List<DayTask>> = _dayTasks

    private val _habitLogs = MutableStateFlow(emptyList<HabitLog>())
    val habitLogs: StateFlow<List<HabitLog>> = _habitLogs

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _habits.emit(habitsRepository.getAllHabits())
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _dayTasks.emit(dayTasksRepository.getAllDayTasks())
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _habitLogs.emit(habitsRepository.getAllHabitLogs())
        }
    }

}