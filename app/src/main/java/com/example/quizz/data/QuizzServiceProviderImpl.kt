package com.example.quizz.data

import com.example.quizz.data.model.QuizzResponse
import com.example.quizz.networking.QuizzApiClient
import com.example.quizz.networking.QuizzService
import io.reactivex.Observable

class QuizzServiceProviderImpl(private val quizzApiClient: QuizzApiClient): QuizzServiceProvider {

    override fun getQuizz(): Observable<QuizzResponse> {
        val quizService = quizzApiClient.getRetrofit().create(QuizzService::class.java)

        return quizService.getQuizz()
    }
}