package com.example.quizz.data.model

data class Question(
    val description: String,
    val marks: Int,
    val options: List<Option>,
    val type: String
)