package com.example.quizz.ui.quizattempt

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.quizz.QuizzServiceLocator
import com.example.quizz.data.model2.Question
import com.example.quizz.data.model2.QuizResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class QuizzAttemptPresenter(private val serviceLocator: QuizzServiceLocator) :
    QuizzAttemptContract.Presenter {

    private lateinit var mQuizResponse: QuizResponse

    private lateinit var view: QuizzAttemptContract.View

    private var mPointer = 0

    private lateinit var liveDataListOfQuestion: LiveData<List<Question>>

    private var mIsReview = false

    private val compositeDisposable = CompositeDisposable()

    private lateinit var mQuestions: ArrayList<Question>

    private lateinit var mUserAttemptQuestions: ArrayList<Question>

    override fun insertView(view: QuizzAttemptContract.View) {
        this.view = view
    }

    override fun getQuizzQuestions() {
        serviceLocator.getDataFromRemote().getQuizz()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success -> handleSuccessResult(success) }, { error -> handleError(error) })
            .addTo(compositeDisposable)
    }

    override fun getUserAttemptedQuiz() {
        val observable = serviceLocator.getDataFromLocalDb().getListOfQuestion()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mIsReview = true
                mPointer = 0
                mUserAttemptQuestions = ArrayList()
                mUserAttemptQuestions.addAll(it)
                displayReviewQuiz((it))
            }.addTo(compositeDisposable)
    }

    private fun displayReviewQuiz(list: List<Question>?) {
        list?.let { view.loadQuestion(it[mPointer]) }
        compositeDisposable.clear()

    }

    private fun handleError(error: Throwable?) {
        Log.i(TAG, "handleError")
    }

    private fun handleSuccessResult(success: QuizResponse?) {
        success?.let {
            //            serviceLocator.getDataFromLocalDb().insertData(it.questions)
            mQuizResponse = success
            displayQuiz(success)
        }
    }

    private fun displayQuiz(success: QuizResponse) {
        if (!mIsReview) {
            mQuestions = ArrayList(success.questions)
            mUserAttemptQuestions = ArrayList()
            for (question in mQuestions) {
                val copyQuestion = question.copy1()
                copyQuestion.resetOptions()
                mUserAttemptQuestions.add(copyQuestion)
            }
            view.hidePreviousBtn()
            view.loadQuestion(mUserAttemptQuestions[mPointer])
        } else {
            view.loadQuestion(success.questions[mPointer])
        }

    }

    override fun onNextClicked() {
        if (mPointer < mUserAttemptQuestions.size) {

            if (mPointer == mUserAttemptQuestions.size - 1) {
                onSubmitClicked()
            } else {
                mPointer++
                view.loadQuestion(mUserAttemptQuestions[mPointer])

                if (mPointer == mUserAttemptQuestions.size - 1) {
                    view.enableSubmitBtn()
                }
                view.enablePreviousBtn()
            }
        }
    }

    override fun onPreviousClicked() {

        mPointer--
        if (mPointer == 0) {
            view.hidePreviousBtn()
        }
        view.enableNextBtn()
        view.loadQuestion(mUserAttemptQuestions[mPointer])
    }

    private fun onSubmitClicked() {
        var userScore = 0
        var maxMark = mUserAttemptQuestions.size * 2

        if (!mIsReview) {
            for (userAttempt in mUserAttemptQuestions) {
                if (mQuestions.contains(userAttempt)) {
                    userScore += userAttempt.marks
                    userAttempt.isAttemptCorrect = true
                }
            }
            view.loadSummaryResult(userScore, maxMark)
            serviceLocator.getDataFromLocalDb().insertData(mUserAttemptQuestions)
        } else {
            for (userAttempt in mUserAttemptQuestions) {
                if (userAttempt.isAttemptCorrect) {
                    userScore += userAttempt.marks
                }
            }
            view.loadSummaryResult(userScore, maxMark)
        }

    }

    companion object {
        const val TAG = "QuizzAttemptPresenter"
    }
}