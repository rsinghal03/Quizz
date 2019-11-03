package com.example.quizz.data.model2

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.quizz.data.database.QuizzTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "QuizzTable")
@TypeConverters(QuizzTypeConverter::class)
data class Question(
    @PrimaryKey
    @SerializedName("id")
    val queId: Int,
    val description: String,
    val marks: Int,
    val options: List<Option>,
    val type: String,
    var isAttemptCorrect: Boolean = false
) {
    fun resetOptions() {
        for (optionEntry in options) {
            optionEntry.is_correct = false
        }
    }

    fun copy1(
        queId: Int = this.queId,
        description: String = this.description,
        marks: Int = this.marks,
        options: List<Option> = this.options,
        type: String = this.type
    ): Question {
        val newInstance = ArrayList<Option>()
        for (option in options) {
            newInstance.add(option.copy())
        }
        return Question(queId, description, marks, newInstance, type)
    }
}