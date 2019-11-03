package com.example.quizz.ui.quizresultdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizz.R
import com.example.quizz.extension.replace
import com.example.quizz.ui.quizattempt.QuizzAttemptFragment
import com.example.quizz.utils.QUIZ_MAX_SCORE
import com.example.quizz.utils.SCORE_OBTAINED
import kotlinx.android.synthetic.main.quiz_result_detail_fragment.*

class QuizResultDetails : Fragment() {

    private lateinit var mMaxScore: String
    private lateinit var mScore: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mScore = it[SCORE_OBTAINED] as String
            mMaxScore = it[QUIZ_MAX_SCORE] as String
        }
    }

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
        val t = "$mScore/$mMaxScore"
        tv_number_scored.text = t
    }

    companion object {
        fun getInstance(maxScore: String, score: String): QuizResultDetails {
            val fragment = QuizResultDetails()
            fragment.arguments = Bundle().apply {
                putString(QUIZ_MAX_SCORE, maxScore)
                putString(SCORE_OBTAINED, score)
            }
            return fragment
        }
    }
}