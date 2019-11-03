package com.example.quizz.data.database

import androidx.room.TypeConverter
import com.example.quizz.data.model2.Option
import com.example.quizz.data.model2.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object QuizzTypeConverter {

    @TypeConverter
    @JvmStatic
    fun listOfOptionToString(list: List<Option>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun stringToListOfOption(string: String): List<Option> {
        val listType = object : TypeToken<List<Option>>(){}.type

        return Gson().fromJson(string, listType)
    }

}