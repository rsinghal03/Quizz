package com.example.quizz.ui.quizattempt

import com.example.quizz.QuizZServiceLocator
import com.example.quizz.data.model.QuizzResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuizzAttemptPresenter(val serviceLocator: QuizZServiceLocator) : QuizzAttemptContract.Presenter {

    override fun getQuizzQuestions() {
        serviceLocator.getDataFromRemote().getQuizz()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ success -> handleSuccessResult(success) }, {error ->  })
            .dispose()
    }

    private fun handleSuccessResult(success: QuizzResponse?) {

    }


}