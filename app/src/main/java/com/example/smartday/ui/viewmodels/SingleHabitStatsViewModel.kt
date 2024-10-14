package com.example.smartday.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartday.data.habits_feature.Habit
import com.example.smartday.data.habits_feature.HabitLog
import com.example.smartday.data.habits_feature.HabitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleHabitStatsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val habitsRepository: HabitsRepository
) : ViewModel() {

    private val habitId: Long = checkNotNull(savedStateHandle["habitId"])

    val habit = habitsRepository.getHabitById(habitId).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        Habit(0L, "", "", 1, "", "", 0)
    )

    private val _habitLogs = MutableStateFlow(emptyList<HabitLog>())
    val habitLogs: StateFlow<List<HabitLog>> = _habitLogs

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _habitLogs.emit(habitsRepository.getAllHabitLogsByHabitId(habitId))
        }
    }

}