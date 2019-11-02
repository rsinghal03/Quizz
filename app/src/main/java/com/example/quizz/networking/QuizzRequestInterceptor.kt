package com.example.quizz.networking

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class QuizzRequestInterceptor : Interceptor {
 val respose = "mock"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url()
        if(respose == "mock") {
            return getMockResponse(request)
        }
        return chain.proceed(request)
    }

    private fun getMockResponse(request: Request): Response {
        val fileName = "quizz.json"
        val responseCode = 200
        return MockDataProvider(fileName).getResponse(request, responseCode)
    }
}