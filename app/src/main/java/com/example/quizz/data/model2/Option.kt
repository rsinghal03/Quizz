package com.example.quizz.data.model2

import android.os.Parcel
import android.os.Parcelable

data class Option(
    val description: String,
    var is_correct: Boolean,
    val remarks: String
) {

    fun copy1(description: String = this.description,
              is_correct: Boolean = this.is_correct,
              remarks: String = this.remarks) = Option(description, is_correct, remarks)

}