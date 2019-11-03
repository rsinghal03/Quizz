package com.example.quizz.ui.quizattempt

import com.example.quizz.data.model2.Question
import com.example.quizz.data.model2.QuizResponse

interface QuizzAttemptContract {

    interface View {
        fun loadQuestion(question: Question)
        fun enableNextBtn()
        fun hidePreviousBtn()
        fun enablePreviousBtn()
        fun enableSubmitBtn()
        fun loadSummaryResult(score: Int, total: Int)
    }

    interface Presenter {
        fun getQuizzQuestions()

        fun getUserAttemptedQuiz()

        fun insertView(view: View)
//        fun checkAnswer(queId: Int, selectedOption: Int)
        fun onNextClicked()
        fun onPreviousClicked()
//        fun onSubmitClicked()
    }
}