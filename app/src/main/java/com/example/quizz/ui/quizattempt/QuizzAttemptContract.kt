package com.example.quizz.ui.quizattempt

import com.example.quizz.data.model.QuizDescription

interface QuizzAttemptContract {

    interface View {
        fun showQuizz(quizzDescription: QuizDescription)
    }

    interface Presenter {
        fun getQuizzQuestions()

        fun insertView(view: View)
    }
}