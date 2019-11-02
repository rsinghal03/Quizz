package com.example.quizz.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizz.data.model2.Question

@Database(
    entities = [Question::class],
    version = 1,
    exportSchema = false
)
abstract class QuizzDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: QuizzDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            QuizzDatabase::class.java, "Quizz.db"
        )
            .build()
    }

    abstract fun getQuizzDao(): QuizzDao

}