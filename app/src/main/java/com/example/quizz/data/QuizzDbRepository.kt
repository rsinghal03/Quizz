package com.example.quizz.data

import android.content.Context
import com.example.quizz.data.database.QuizzDatabase
import com.example.quizz.data.model2.Question
import io.reactivex.Observable
import java.util.concurrent.Executors

class QuizzDbRepository(private val context: Context) {
    private val diskIoExecutor = Executors.newSingleThreadExecutor()

    private val db by lazy {
        QuizzDatabase.invoke(context)
    }

    fun insertData(listOfQuestion: List<Question>) {
        diskIoExecutor.execute {
            db.runInTransaction {
                db.getQuizzDao().insertListOfQuiz(listOfQuestion)
            }
        }
    }

    fun getListOfQuestion(): Observable<List<Question>> {
        return db.getQuizzDao().getListOfQuestion()
    }
}