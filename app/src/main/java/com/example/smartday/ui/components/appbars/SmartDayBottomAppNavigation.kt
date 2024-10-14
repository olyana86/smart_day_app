package com.example.smartday.ui.components.appbars

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.smartday.R
import com.example.smartday.navigation.BottomNavItem

@Composable
fun SmartDayBottomAppNavigation(navController: NavHostController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val bottomItems = listOf(
            BottomNavItem.Main,
            BottomNavItem.AllHabits,
            BottomNavItem.Pomodoro,
            BottomNavItem.Stretching
        )
        bottomItems.forEach { item ->
            val iconVector = when (item.route) {
                "Main" -> ImageVector.vectorResource(id = R.drawable.day_tasks_icon)
                "AllHabits" -> ImageVector.vectorResource(id = R.drawable.all_habits_icon)
                "Pomodoro" -> ImageVector.vectorResource(id = R.drawable.pomodoro_icon)
                else -> {
                    ImageVector.vectorResource(id = R.drawable.stretching_man_icon)
                }
            }

            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        modifier = androidx.compose.ui.Modifier.size(24.dp),
                        imageVector = iconVector,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            )
        }
    }
}
