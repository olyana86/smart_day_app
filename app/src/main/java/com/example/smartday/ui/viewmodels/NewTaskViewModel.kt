package com.example.smartday.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartday.data.daytasks_feature.DayTask
import com.example.smartday.data.daytasks_feature.DayTasksRepository
import com.example.smartday.data.daytasks_feature.EisenhowerTag
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val dayTasksRepository: DayTasksRepository
) : ViewModel() {

    private val _eisenhowerTags = MutableStateFlow(emptyList<EisenhowerTag>())
    val eisenhowerTags: StateFlow<List<EisenhowerTag>> = _eisenhowerTags

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _eisenhowerTags.emit(dayTasksRepository.getAllEisenhowerTags())
        }
    }

    fun insertNewDayTask(dayTask: DayTask) {
        viewModelScope.launch {
            dayTasksRepository.insertDayTask(dayTask)
        }
    }
}