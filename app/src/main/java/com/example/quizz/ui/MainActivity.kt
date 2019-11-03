package com.example.quizz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizz.R
import com.example.quizz.extension.add
import com.example.quizz.ui.listofquiz.ListOfQuizFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add(ListOfQuizFragment.instance, R.id.container, false)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount >= 1){
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
