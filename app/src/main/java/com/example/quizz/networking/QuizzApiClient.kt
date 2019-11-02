package com.example.quizz.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class QuizzApiClient {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.quizz.com/") // no such base url exits..its just for demo purpose
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(QuizzRequestInterceptor())
            .build()
    }

    companion object {
        const   val READ_WRITE_TIMEOUT: Long = 60
        const   val CONNECTION_TIMEOUT: Long = 60
    }
}