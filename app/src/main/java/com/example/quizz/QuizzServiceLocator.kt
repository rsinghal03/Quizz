package com.example.quizz

import com.example.quizz.data.QuizzServiceProvider

interface QuizzServiceLocator {

    fun getDataFromRemote(): QuizzServiceProvider
}

class QuizzServiceLocatorImpl(val quizzServiceProvider: QuizzServiceProvider) : QuizzServiceLocator {

    override fun getDataFromRemote(): QuizzServiceProvider {
        return quizzServiceProvider
    }

}