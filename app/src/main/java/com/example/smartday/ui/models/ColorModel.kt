package com.example.smartday.ui.models

data class ColorModel(
    val id: Int,
    val hex: String
)

fun getColors() = listOf(
    ColorModel(1, "#E9A2A2"),
    ColorModel(2, "#D9E9A2"),
    ColorModel(3, "#A2E9B3"),
    ColorModel(4, "#A2BBE9"),
    ColorModel(5, "#E9A2E6"),
    ColorModel(6, "#A2E8E9"),
    ColorModel(7, "#E9E6A2"),
    ColorModel(8, "#E9C8A2")
)
