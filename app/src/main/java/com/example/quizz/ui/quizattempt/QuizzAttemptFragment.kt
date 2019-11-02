package com.example.quizz.ui.quizattempt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.quizz.QuizzApplication
import com.example.quizz.R
import com.example.quizz.data.model.QuizDescription
import kotlinx.android.synthetic.main.quizz_attempt_fragment.*
import javax.inject.Inject

class QuizzAttemptFragment : Fragment(), QuizzAttemptContract.View {

    @Inject
    lateinit var quizzAttemptPresenter: QuizzAttemptContract.Presenter

    companion object {
        val instance = QuizzAttemptFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QuizzApplication.getInstance().quizzDiComponent.inject(this)
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
        quizzAttemptPresenter.getQuizzQuestions()
    }

    override fun showQuizz(quizzDescription: QuizDescription) {
        tv_question_description.text = quizzDescription.questions[0].description
        rg_single_choice_holder.visibility = View.VISIBLE
        for ((index, option) in quizzDescription.questions[0].options.withIndex()) {
            val radioButton = LayoutInflater.from(requireContext())
                .inflate(
                    R.layout.rb_single_choice,
                    fl_all_options_type_holder,
                    false
                ) as RadioButton

            radioButton.text = option.description
            radioButton.setOnCheckedChangeListener { buttonView, isChecked ->

            }
            rg_single_choice_holder.addView(radioButton, index)
        }
    }
}
