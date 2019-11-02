package com.example.quizz.data.model2

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.quizz.data.database.QuizzTypeConverter

@Entity(tableName = "QuizzTable")
@TypeConverters(QuizzTypeConverter::class)
data class Question(
    @PrimaryKey
    val queId: Int,
    val description: String,
    val marks: Int,
    val options: List<Option>,
    val type: String
)