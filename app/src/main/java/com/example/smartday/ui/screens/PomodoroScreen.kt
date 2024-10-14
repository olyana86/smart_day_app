package com.example.smartday.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smartday.R
import com.example.smartday.data.pomodoro_feature.PomodoroResult
import com.example.smartday.ui.components.timers.PomodoroTimer
import com.example.smartday.ui.dialogs.PomodoroInfoDialog
import com.example.smartday.ui.viewmodels.PomodoroViewModel

@Composable
fun PomodoroScreen(
    navController: NavHostController
) {
    val viewModel: PomodoroViewModel = hiltViewModel()
    val pomodoroResult = viewModel.pomodoroResult.collectAsState(initial = PomodoroResult(1, 0))
    val title = stringResource(id = R.string.pomodoro_title)
    val shortDescription = stringResource(id = R.string.pomodoro_short_description)
    val shouldShowInfoDialog = remember { mutableStateOf(false) }

    if (shouldShowInfoDialog.value) {
        PomodoroInfoDialog(shouldShow = shouldShowInfoDialog)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineBreak = LineBreak.Paragraph)) {
                        append(shortDescription)
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            ElevatedButton(onClick = {
                shouldShowInfoDialog.value = true
            }) {
                Text(text = "Описание методики")
            }

            Spacer(modifier = Modifier.height(16.dp))

            PomodoroTimer(
                onChange = { newResult ->
                    val newTotal = pomodoroResult.value.pomodoroTotal + newResult
                    viewModel.updatePomodoroResult(
                        PomodoroResult(1, newTotal)
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (pomodoroResult.value.pomodoroTotal != 0) {
                Row {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.red_tomato_icon),
                        contentDescription = "Red tomato",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.red_tomato_icon),
                        contentDescription = "Red tomato",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.red_tomato_icon),
                        contentDescription = "Red tomato",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                Text(text = "Заработано помидор: ${pomodoroResult.value.pomodoroTotal}")

                Spacer(modifier = Modifier.height(16.dp))

                ElevatedButton(onClick = {
                    viewModel.updatePomodoroResult(
                        PomodoroResult(1, 0)
                    )
                }) {
                    Text(text = "Обнулить результат")
                }
            }
        }
    }
}