package com.example.smartday.ui.models

data class DayOfWeekModel(
    val id: String,
    val title: String,
    var isChosen: Boolean
)

fun getDaysOfWeekForChooser() = listOf(
    DayOfWeekModel("1", "ПН", true),
    DayOfWeekModel("2", "ВТ", true),
    DayOfWeekModel("3", "СР", true),
    DayOfWeekModel("4", "ЧТ", true),
    DayOfWeekModel("5", "ПТ", true),
    DayOfWeekModel("6", "СБ", true),
    DayOfWeekModel("7", "ВС", true)
)