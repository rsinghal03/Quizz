package com.example.quizz.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizz.data.model2.Option
import com.example.quizz.data.model2.Question

@Dao
interface QuizzDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListOfQuiz(listOfQuestion: List<Question>)

    @Query("select * from QuizzTable")
    fun getListOfQuestion(): LiveData<List<Question>>

}