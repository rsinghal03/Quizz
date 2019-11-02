package com.example.quizz.dependencyInjection

import com.example.quizz.ui.quizattempt.QuizzAttemptFragment
import dagger.Component

@Component(modules = [QuizzDiModule::class])
interface QuizzDiComponent {

    fun inject(quizzAttemptFragment: QuizzAttemptFragment)
}