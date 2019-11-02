package com.example.quizz.data.model

data class QuizDescription(
    val creator_id: String,
    val creator_name: String,
    val date_published: String,
    val description: String,
    val difficulty: String,
    val max_marks: Int,
    val questions: List<Question>,
    val title: String
)