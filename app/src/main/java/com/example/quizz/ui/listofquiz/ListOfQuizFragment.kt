package com.example.quizz.ui.listofquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizz.R
import com.example.quizz.extension.replace
import com.example.quizz.ui.quizattempt.QuizzAttemptFragment
import kotlinx.android.synthetic.main.list_of_quiz_fragment.*

class ListOfQuizFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return LayoutInflater.from(inflater.context).inflate(R.layout.list_of_quiz_fragment,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quiz_start_btn.setOnClickListener {
            replace(QuizzAttemptFragment.getInstance(), R.id.container)
        }
    }

    companion object {
        val instance = ListOfQuizFragment()
    }
}