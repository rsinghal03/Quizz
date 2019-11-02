package com.example.quizz.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.quizz.data.model2.Question

@Dao
interface QuizzDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListOfQuiz(listOfQuestion: List<Question>)

}