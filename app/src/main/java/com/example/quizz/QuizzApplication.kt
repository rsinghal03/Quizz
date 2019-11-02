package com.example.quizz

import android.app.Application
import com.example.quizz.dependencyInjection.DaggerQuizzDiComponent
import com.example.quizz.dependencyInjection.QuizzDiComponent

class QuizzApplication : Application() {

    lateinit var quizzDiComponent: QuizzDiComponent

    init {
        initDagger()
    }

    private fun initDagger() {
        quizzDiComponent = DaggerQuizzDiComponent.create()
    }

    companion object {

        private var instance: QuizzApplication? = null

        fun getInstance(): QuizzApplication =
            instance ?: QuizzApplication().also {
                instance = it
            }
    }
}