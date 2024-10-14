package com.example.smartday.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.smartday.R
import com.example.smartday.ui.theme.secondaryLight

@Composable
fun AppInfoScreen(
    navController: NavHostController
) {
    val introBeginning = stringResource(id = R.string.app_info_screen_text_beginning)
    val introEnd = stringResource(id = R.string.app_info_screen_text_end)
    val frogMarkTitle = stringResource(id = R.string.my_day_frog_mark_title)
    val frogMarkDescription = stringResource(id = R.string.my_day_frog_mark_text)
    val priorityMarkTitle = stringResource(id = R.string.my_day_priority_mark_title)
    val priorityMarkDescription = stringResource(id = R.string.my_day_priority_mark_text)
    val eisenhowerMarkTitle = stringResource(id = R.string.my_day_eisenhower_mark_title)
    val eisenhowerMarkDescription = stringResource(id = R.string.my_day_eisenhower_mark_text)
    val timeBlockMarkTitle = stringResource(id = R.string.my_day_time_block_mark_title)
    val timeBlockMarkDescription = stringResource(id = R.string.my_day_time_block_mark_text)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineBreak = LineBreak.Paragraph)) {
                append(introBeginning)
            }
        })
        Text(
            text = frogMarkTitle,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineBreak = LineBreak.Paragraph)) {
                append(frogMarkDescription)
            }
        })
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = priorityMarkTitle,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineBreak = LineBreak.Paragraph)) {
                append(priorityMarkDescription)
            }
        })
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = eisenhowerMarkTitle,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineBreak = LineBreak.Paragraph)) {
                append(eisenhowerMarkDescription)
            }
        })
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = timeBlockMarkTitle,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineBreak = LineBreak.Paragraph)) {
                append(timeBlockMarkDescription)
            }
        })
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineBreak = LineBreak.Paragraph)) {
                append(introEnd)
            }
        })
        ElevatedButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = secondaryLight,
                disabledContentColor = secondaryLight
            )
        ) {
            Text(text = "ОK")
        }
    }
}