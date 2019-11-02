package com.example.quizz.dependencyInjection

import dagger.Component

@Component(modules = [QuizzDiModule::class])
interface QuizzDiComponent {
}