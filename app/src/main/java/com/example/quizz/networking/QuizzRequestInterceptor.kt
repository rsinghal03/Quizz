package com.example.quizz.networking

import com.example.quizz.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class QuizzRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url()
        if(BuildConfig.FLAVOR == "mock") {
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