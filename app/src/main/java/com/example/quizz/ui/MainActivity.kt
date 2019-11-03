package com.example.quizz.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.example.quizz.R
import com.example.quizz.extension.add
import com.example.quizz.ui.listofquiz.ListOfQuizFragment
import com.example.quizz.ui.quizattempt.QuizzAttemptFragment
import com.example.quizz.ui.quizresultdetails.QuizResultDetails

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add(ListOfQuizFragment.instance, R.id.container, false)
    }

    override fun onBackPressed() {
        when {
            supportFragmentManager.backStackEntryCount == 2 -> supportFragmentManager.popBackStack(
                QuizzAttemptFragment::class.java.canonicalName,
                POP_BACK_STACK_INCLUSIVE
            )
            supportFragmentManager.backStackEntryCount == 1 -> supportFragmentManager.popBackStack()
            else -> finish()
        }
    }
}