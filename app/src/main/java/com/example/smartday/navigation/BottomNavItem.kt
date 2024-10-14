package com.example.smartday.navigation


sealed class BottomNavItem(
    val route: String,
    val title: String
) {
    object Main : BottomNavItem(
        route = "Main",
        title = "Мой день"
    )

    object AllHabits : BottomNavItem(
        route = "AllHabits",
        title = "Привычки"
    )

    object Pomodoro : BottomNavItem(
        route = "Pomodoro",
        title = "Помодоро"
    )

    object Stretching : BottomNavItem(
        route = "Stretching",
        title = "Потянись!"
    )
}