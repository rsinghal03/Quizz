package com.example.quizz.ui.quizattempt

import com.example.quizz.data.model2.QuizResponse

interface QuizzAttemptContract {

    interface View {
        fun showQuizz(quizResponse: QuizResponse)
    }

    interface Presenter {
        fun getQuizzQuestions()

        fun insertView(view: View)
    }
}