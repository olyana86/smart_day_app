package com.example.smartday.ui.components.appbars

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.smartday.R
import com.example.smartday.ui.dialogs.DailyMotivationDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartDayTopAppBar(navController: NavHostController) {
    val shouldShowDialog = remember { mutableStateOf(false) }
    if (shouldShowDialog.value) {
        DailyMotivationDialog(shouldShow = shouldShowDialog)
    }

    TopAppBar(
        title = {
            ElevatedButton(
                onClick = {
                    shouldShowDialog.value = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.motivation_sun_icon),
                    contentDescription = "Motivation",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Мотивация дня",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate("ProfileStats")
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.profile_icon),
                    contentDescription = "Profile Stats",
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = {
                navController.navigate("AppInfo")
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.marks_info_icon),
                    contentDescription = "App Info",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}