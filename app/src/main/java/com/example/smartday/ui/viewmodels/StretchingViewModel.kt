package com.example.smartday.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StretchingViewModel @Inject constructor() : ViewModel() {

    private var currentTime = 0

    val timer = flow {
        while (true) {
            delay(1000L)
            emit(currentTime++)
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), 0
    )


}