package com.example.smartday.ui.models

data class StretchingExercise(val text: String, val progress: Float)

val stretchingExercises = listOf(
    StretchingExercise(
        "Вытяни руки вверх, соедини ладони. Максимально потянись.",
        (1 / 6.toFloat())
    ),
    StretchingExercise(
        "Заведи руки за спину, сцепи пальцы и выпрями локти, прогибаясь в спине.",
        (2 / 6.toFloat())
    ),
    StretchingExercise(
        "Вытяни руки перед собой, соедини пальцы и согни локти, округляя спину.\n" +
                "Опусти голову.", (3 / 6.toFloat())
    ),
    StretchingExercise("Медленно наклоняй голову к правому и к левому плечу.", (4 / 6.toFloat())),
    StretchingExercise("Повращай плечами вперед и назад.", (5 / 6.toFloat()))
)
