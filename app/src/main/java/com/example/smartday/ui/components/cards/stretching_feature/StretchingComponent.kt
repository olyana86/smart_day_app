package com.example.smartday.ui.components.cards.stretching_feature

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartday.ui.models.stretchingExercises
import kotlinx.coroutines.delay

@Composable
fun StretchingComponent(
    onTimeStop: (Boolean) -> Unit,
    onFinish: (Boolean) -> Unit
) {
    val isDone = remember { mutableStateOf(false) }
    val exercisesCompleted = remember { mutableStateOf(false) }
    val stretchingCompleted = remember { mutableStateOf(false) }
    val exercises = stretchingExercises
    val exerciseIndex = remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        for (i in 0..5) {
            exerciseIndex.intValue = i
            delay(20000)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        if (exerciseIndex.intValue != 5) {
            val currentExercise = exercises[exerciseIndex.intValue]
            StretchingExerciseCard(
                modifier = Modifier,
                barProgress = currentExercise.progress,
                exerciseText = currentExercise.text
            )
        }

        if (exerciseIndex.intValue == 5) {
            onTimeStop(true)
            exercisesCompleted.value = true
            StretchingFinalCard(
                barProgress = 1.0f,
                onFinish = {
                    stretchingCompleted.value = true
                }
            )
        }

        if (exercisesCompleted.value && stretchingCompleted.value) {
            isDone.value = true
            onFinish(isDone.value)
        }
    }
}