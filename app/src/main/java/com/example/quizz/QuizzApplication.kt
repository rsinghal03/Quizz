package com.example.quizz

import android.app.Application
import com.facebook.stetho.Stetho

class QuizzApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerInit.getInstance(this)
        Stetho.initializeWithDefaults(this)
    }

    companion object {

        private var instance: QuizzApplication? = null

        fun getInstance(): QuizzApplication =
            instance ?: QuizzApplication().also {
                instance = it
            }
    }
}