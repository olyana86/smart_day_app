package com.example.smartday.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smartday.R
import com.example.smartday.ui.components.cards.stretching_feature.StretchingComponent
import com.example.smartday.ui.dialogs.StretchingCongratulationDialog
import com.example.smartday.ui.viewmodels.StretchingViewModel
import com.example.smartday.utils.formatTimeFromSeconds


@Composable
fun StretchingScreen(
    navController: NavHostController
) {
    val viewModel: StretchingViewModel = hiltViewModel()
    val title = stringResource(id = R.string.stretching_title)
    val stretchingDescription = stringResource(id = R.string.stretching_description)
    val shouldShowExercises = remember { mutableStateOf(false) }
    val shouldShowDialog = remember { mutableStateOf(false) }
    val shouldShowTimer = remember { mutableStateOf(true) }

    if (shouldShowDialog.value) {
        StretchingCongratulationDialog(shouldShow = shouldShowDialog)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = ParagraphStyle(lineBreak = LineBreak.Paragraph)) {
                    append(stretchingDescription)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (!shouldShowExercises.value) {
            ElevatedButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    shouldShowExercises.value = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(text = "Начать")
            }
        }
        if (shouldShowExercises.value) {
            Column {
                Box(
                    modifier = Modifier.weight(0.6f),
                    contentAlignment = Alignment.Center
                ) {
                    StretchingComponent(
                        onTimeStop = {
                            shouldShowTimer.value = false
                        },
                        onFinish = {
                            shouldShowDialog.value = true
                            shouldShowExercises.value = false
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (shouldShowTimer.value) {
                    val newTime by viewModel.timer.collectAsState()
                    Box(
                        modifier = Modifier
                            .weight(0.4f)
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = formatTimeFromSeconds(newTime.toLong()),
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}