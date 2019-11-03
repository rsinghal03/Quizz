package com.example.quizz.ui.quizattempt

import android.util.Log
import com.example.quizz.QuizzServiceLocator
import com.example.quizz.data.model2.Question
import com.example.quizz.data.model2.QuizResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuizzAttemptPresenter(val serviceLocator: QuizzServiceLocator) :
    QuizzAttemptContract.Presenter {

    private lateinit var mQuizResponse: QuizResponse

    private lateinit var view: QuizzAttemptContract.View

    private var mPointer = 0

    private var mIsReview = false

    private lateinit var mQuestions: ArrayList<Question>

    private lateinit var mUserAttemptQuestions: ArrayList<Question>

    override fun insertView(view: QuizzAttemptContract.View) {
        this.view = view
    }

    override fun getQuizzQuestions() {
        val dispose = serviceLocator.getDataFromRemote().getQuizz()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success -> handleSuccessResult(success) }, { error -> handleError(error) })
    }

    override fun getUserAttemptedQuiz() {
        val liveData = serviceLocator.getDataFromLocalDb().getListOfQuestion()
        liveData.observeForever {
            mIsReview = true
            mPointer = 0
            mUserAttemptQuestions = ArrayList()
            mUserAttemptQuestions.addAll(it)
            displayReviewQuiz((it))
        }
    }

    private fun displayReviewQuiz(list: List<Question>?) {
        list?.let { view.loadQuestion(it[mPointer]) }
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
        if(!mIsReview) {
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
        var maxMark = mUserAttemptQuestions.size*2

        if(!mIsReview) {
            for (userAttempt in mUserAttemptQuestions) {
                if (mQuestions.contains(userAttempt)) {
                    userScore += userAttempt.marks
                    userAttempt.isAttemptCorrect = true
                }
            }
            view.loadSummaryResult(userScore, maxMark)
            serviceLocator.getDataFromLocalDb().insertData(mUserAttemptQuestions)
        } else {
            for(userAttempt in mUserAttemptQuestions) {
                if(userAttempt.isAttemptCorrect){
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