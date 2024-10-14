package com.example.smartday.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartday.data.daytasks_feature.DayTask
import com.example.smartday.data.daytasks_feature.DayTasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dayTasksRepository: DayTasksRepository
) : ViewModel() {

    private val dayTaskId: Long = checkNotNull(savedStateHandle["dayTaskId"])

    val dayTask = dayTasksRepository.getDayTaskById(dayTaskId).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        DayTask(
            0L, "", "", "", "", "",
            0, 0, 0, 0, ""
        )
    )

    fun updateDayTask(dayTask: DayTask) {
        viewModelScope.launch(Dispatchers.IO) {
            dayTasksRepository.updateDayTask(dayTask)
        }
    }

    fun deleteDayTask(dayTask: DayTask) {
        viewModelScope.launch(Dispatchers.IO) {
            dayTasksRepository.deleteDayTask(dayTask)
        }
    }
}