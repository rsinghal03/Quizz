package com.example.quizz.networking

import com.example.quizz.data.model2.QuizResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface QuizzService {

    @GET(".")
    fun getQuizz(): Observable<QuizResponse>
}