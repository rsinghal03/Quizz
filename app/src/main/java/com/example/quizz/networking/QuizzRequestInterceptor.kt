package com.example.quizz.networking

import okhttp3.Interceptor
import okhttp3.Response

class QuizzRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url()
        return chain.proceed(request)
    }
}