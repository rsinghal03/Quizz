package com.example.quizz.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.quizz.data.database.QuizzDatabase
import com.example.quizz.data.model2.Option
import com.example.quizz.data.model2.Question
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

    fun getListOfQuestion(): LiveData<List<Question>> {
        return db.getQuizzDao().getListOfQuestion()
    }
}