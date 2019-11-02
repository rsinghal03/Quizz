package com.example.quizz.ui.quizattempt

import android.util.Log
import com.example.quizz.QuizzServiceLocator
import com.example.quizz.data.model2.QuizResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuizzAttemptPresenter(val serviceLocator: QuizzServiceLocator) : QuizzAttemptContract.Presenter {

    lateinit var view: QuizzAttemptContract.View

    override fun insertView(view: QuizzAttemptContract.View) {
        this.view = view
    }

    override fun getQuizzQuestions() {
        val dispose = serviceLocator.getDataFromRemote().getQuizz()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ success -> handleSuccessResult(success) }, {error -> handleError(error)  })
    }

    private fun handleError(error: Throwable?) {
        Log.i(TAG, "handleError")
    }

    private fun handleSuccessResult(success: QuizResponse?) {
        success?.let {
            serviceLocator.getDataFromLocalDb().insertData(it.questions)
            view.showQuizz(it)
        }
    }

    companion object {
        const val TAG = "QuizzAttemptPresenter"
    }
}