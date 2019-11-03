package com.example.quizz.ui.quizresultdetails

interface QuizzResultDetailsContract {

    interface View {

    }

    interface Presenter {
        fun getUserAttemptedQuizFromLocalDb()
    }

}