package com.example.smartday.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartday.data.pomodoro_feature.PomodoroRepository
import com.example.smartday.data.pomodoro_feature.PomodoroResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PomodoroViewModel @Inject constructor(
    private val pomodoroRepository: PomodoroRepository
) : ViewModel() {

    private val _pomodoroResult = MutableStateFlow(PomodoroResult(1, 0))
    val pomodoroResult: StateFlow<PomodoroResult> = _pomodoroResult

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _pomodoroResult.emit(pomodoroRepository.getPomodoroResult())
        }
    }

    fun updatePomodoroResult(pomodoroResult: PomodoroResult) {
        viewModelScope.launch(Dispatchers.IO) {
            pomodoroRepository.updatePomodoroResult(pomodoroResult)
            _pomodoroResult.emit(pomodoroRepository.getPomodoroResult())
        }
    }
}