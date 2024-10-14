package com.example.smartday.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smartday.ui.screens.AllHabitsScreen
import com.example.smartday.ui.screens.AppInfoScreen
import com.example.smartday.ui.screens.HabitEditScreen
import com.example.smartday.ui.screens.MainScreen
import com.example.smartday.ui.screens.NewHabitScreen
import com.example.smartday.ui.screens.NewTaskScreen
import com.example.smartday.ui.screens.PomodoroScreen
import com.example.smartday.ui.screens.ProfileStatsScreen
import com.example.smartday.ui.screens.SingleHabitStatsScreen
import com.example.smartday.ui.screens.StretchingScreen
import com.example.smartday.ui.screens.TaskEditScreen

@Composable
fun SmartDayNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "Main") {

        composable("Main") { MainScreen(navController = navController) }
        composable("ProfileStats") { ProfileStatsScreen(navController = navController) }
        composable("AppInfo") { AppInfoScreen(navController = navController) }

        composable("AllHabits") { AllHabitsScreen(navController = navController) }
        composable("NewHabit") { NewHabitScreen(navController = navController) }
        composable(
            "HabitEdit/{habitId}",
            arguments = listOf(navArgument("habitId")
            { type = NavType.LongType }
            )
        ) { backStackEntry ->
            HabitEditScreen(
                navController = navController,
                habitId = backStackEntry.arguments?.getLong("habitId") ?: 0L
            )
        }
        composable(
            "SingleHabitStats/{habitId}",
            arguments = listOf(navArgument("habitId")
            { type = NavType.LongType }
            )
        ) { backStackEntry ->
            SingleHabitStatsScreen(
                navController = navController,
                habitId = backStackEntry.arguments?.getLong("habitId") ?: 0L
            )
        }

        composable(
            "NewTask/{currentDate}",
            arguments = listOf(navArgument("currentDate")
            { type = NavType.StringType }
            )
        ) { backStackEntry ->
            NewTaskScreen(
                navController = navController,
                currentDate = backStackEntry.arguments?.getString("currentDate") ?: ""
            )
        }
        composable(
            "TaskEdit/{dayTaskId}",
            arguments = listOf(navArgument("dayTaskId")
            { type = NavType.LongType }
            )
        ) { backStackEntry ->
            TaskEditScreen(
                navController = navController,
                dayTaskId = backStackEntry.arguments?.getLong("dayTaskId") ?: 0L
            )
        }

        composable("Pomodoro") { PomodoroScreen(navController = navController) }
        composable("Stretching") { StretchingScreen(navController = navController) }

    }
}