package com.example.quizz

import android.content.Context
import com.example.quizz.dependencyInjection.DaggerQuizzDiComponent
import com.example.quizz.dependencyInjection.QuizzDiComponent
import com.example.quizz.dependencyInjection.QuizzDiModule


class DaggerInit private constructor(context: Context) {

    lateinit var quizzDiComponent: QuizzDiComponent


    init {
        initDagger(context)
    }

    private fun initDagger(context: Context) {
        quizzDiComponent = DaggerQuizzDiComponent
            .builder()
            .quizzDiModule(QuizzDiModule(context))
            .build()
    }

    companion object {
        var instance: DaggerInit? = null

        fun getInstance(context: Context): DaggerInit =
            instance ?: DaggerInit(context).also {
                instance = it
            }
    }
}