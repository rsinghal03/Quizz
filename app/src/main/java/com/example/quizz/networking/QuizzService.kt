package com.example.quizz.networking

import com.example.quizz.data.model.QuizzResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface QuizzService {

    @GET(".")
    fun getQuizz(): Observable<QuizzResponse>
}