package com.example.quizz

import com.example.quizz.data.QuizzDbRepository
import com.example.quizz.data.QuizzServiceProvider

interface QuizzServiceLocator {

    fun getDataFromRemote(): QuizzServiceProvider

    fun getDataFromLocalDb(): QuizzDbRepository
}

class QuizzServiceLocatorImpl(
    private val quizzServiceProvider: QuizzServiceProvider,
    val dbRepository: QuizzDbRepository
) : QuizzServiceLocator {

    override fun getDataFromLocalDb(): QuizzDbRepository {
        return dbRepository
    }

    override fun getDataFromRemote(): QuizzServiceProvider {
        return quizzServiceProvider
    }

}