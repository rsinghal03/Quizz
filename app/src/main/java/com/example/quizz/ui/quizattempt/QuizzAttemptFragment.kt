package com.example.quizz.ui.quizattempt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.quizz.DaggerInit
import com.example.quizz.R
import com.example.quizz.data.model2.Question
import com.example.quizz.extension.gone
import com.example.quizz.extension.replace
import com.example.quizz.extension.visible
import com.example.quizz.ui.quizresultdetails.QuizResultDetails
import com.example.quizz.utils.IS_REVIEW
import kotlinx.android.synthetic.main.quizz_attempt_fragment.*
import javax.inject.Inject

class QuizzAttemptFragment : Fragment(), QuizzAttemptContract.View, View.OnClickListener {

    @Inject
    lateinit var quizzAttemptPresenter: QuizzAttemptContract.Presenter

    var mIsReview: Boolean = false

    companion object {
        fun getInstance(isReview: Boolean = false): QuizzAttemptFragment {
            val fragment = QuizzAttemptFragment()

            fragment.apply {
                val bundle = Bundle()
                bundle.putBoolean(IS_REVIEW, isReview)
                arguments = bundle
            }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIsReview = arguments?.getBoolean(IS_REVIEW)!!
        DaggerInit.instance?.quizzDiComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return LayoutInflater.from(inflater.context)
            .inflate(R.layout.quizz_attempt_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizzAttemptPresenter.insertView(this)
        if (!mIsReview) {
            quizzAttemptPresenter.getQuizzQuestions()
        } else {
            quizzAttemptPresenter.getUserAttemptedQuiz()
        }
        img_quiz_attempt_next.setOnClickListener(this)
        img_quiz_attempt_previous.setOnClickListener(this)
    }

    override fun loadQuestion(question: Question) {
        tv_question_description.text = question.description
        rg_single_choice_holder.removeAllViews()

        rg_single_choice_holder.visibility = View.VISIBLE

        if (mIsReview) {
            if (question.isAttemptCorrect) {
                tv_question_description.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
            } else {
                tv_question_description.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
            }
        }

        for ((index, option) in question.options.withIndex()) {
            val radioButton = LayoutInflater.from(requireContext())
                .inflate(
                    R.layout.rb_single_choice,
                    fl_all_options_type_holder,
                    false
                ) as RadioButton
            radioButton.text = option.description
            radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    question.resetOptions()
                    rg_single_choice_holder.clearCheck()
                    option.is_correct = true
                    buttonView.isChecked = true
                }
            }
            radioButton.isChecked = option.is_correct
            rg_single_choice_holder.addView(radioButton, index)
        }
    }

    override fun loadSummaryResult(score: Int, total: Int) {
        replace(QuizResultDetails.getInstance(total.toString(), score.toString()), R.id.container, false)
    }

    override fun enableNextBtn() {
        img_quiz_attempt_next.setImageResource(R.drawable.ic_next_circle_filled)
    }

    override fun hidePreviousBtn() {
        img_quiz_attempt_previous.gone()
    }

    override fun enableSubmitBtn() {
        img_quiz_attempt_next.setImageResource(R.drawable.ic_submit_btn)
    }

    override fun enablePreviousBtn() {
        img_quiz_attempt_previous.visible()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_quiz_attempt_next -> quizzAttemptPresenter.onNextClicked()
            R.id.img_quiz_attempt_previous -> quizzAttemptPresenter.onPreviousClicked()
            else -> {
                return
            }
        }
    }
}
