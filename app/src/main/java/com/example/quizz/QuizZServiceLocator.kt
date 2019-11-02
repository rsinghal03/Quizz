package com.example.quizz

import com.example.quizz.data.QuizzServiceProvider

interface QuizZServiceLocator {

    fun getDataFromRemote(): QuizzServiceProvider
}

class QuizzServiceLocatorImpl : QuizZServiceLocator {

    override fun getDataFromRemote(): QuizzServiceProvider {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}