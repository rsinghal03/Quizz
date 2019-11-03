package com.example.quizz.ui.quizresultdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizz.R
import com.example.quizz.extension.replace
import com.example.quizz.ui.quizattempt.QuizzAttemptFragment
import kotlinx.android.synthetic.main.quiz_result_detail_fragment.*

class QuizResultDeatils : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return LayoutInflater.from(inflater.context)
            .inflate(R.layout.quiz_result_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        review_btn.setOnClickListener {
            replace(QuizzAttemptFragment.getInstance(true), R.id.container,false)
        }
    }

    companion object {
        val instance = QuizResultDeatils()
    }
}