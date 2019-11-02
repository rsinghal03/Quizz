package com.example.quizz.data

import com.example.quizz.data.model2.QuizResponse
import io.reactivex.Observable

interface QuizzServiceProvider {

    fun getQuizz(): Observable<QuizResponse>
}