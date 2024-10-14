package com.example.smartday.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartday.data.habits_feature.Habit
import com.example.smartday.data.habits_feature.HabitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewHabitViewModel @Inject constructor(
    private val habitsRepository: HabitsRepository
) : ViewModel() {

    fun insertNewHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitsRepository.insertHabit(habit)
        }
    }
}