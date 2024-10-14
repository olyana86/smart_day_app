package com.example.smartday.ui.models

fun getProfileRank(smartResult: Int): String {

    var rank = ""

    when (smartResult) {
        0 -> rank = "Надо хотя бы попробовать"
        in 1..10 -> rank = "Я хотя бы пытаюсь"
        in 11..24 -> rank = "Я уже что-то делаю"
        in 25..39 -> rank = "У меня кое-что получается"
        in 40..59 -> rank = "Я хорошо иду!"
        in 60..74 -> rank = "Я очень хорошо справляюсь"
        in 75..89 -> rank = "У меня с эффективностью всё отлично"
        in 90..99 -> rank = "Я живу потрясающе эффективно!"
        100 -> rank = "Я - лучшая версия себя"
    }
    return rank
}