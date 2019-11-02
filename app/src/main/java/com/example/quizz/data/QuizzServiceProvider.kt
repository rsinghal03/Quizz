package com.example.quizz.data

import com.example.quizz.data.model.QuizzResponse
import io.reactivex.Observable

interface QuizzServiceProvider {

    fun getQuizz(): Observable<QuizzResponse>
}