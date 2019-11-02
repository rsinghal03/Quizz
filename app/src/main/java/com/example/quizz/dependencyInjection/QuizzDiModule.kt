package com.example.quizz.dependencyInjection

import android.content.Context
import com.example.quizz.QuizzServiceLocator
import com.example.quizz.QuizzServiceLocatorImpl
import com.example.quizz.data.QuizzDbRepository
import com.example.quizz.data.QuizzServiceProvider
import com.example.quizz.data.QuizzServiceProviderImpl
import com.example.quizz.networking.QuizzApiClient
import com.example.quizz.ui.quizattempt.QuizzAttemptContract
import com.example.quizz.ui.quizattempt.QuizzAttemptPresenter
import dagger.Module
import dagger.Provides

@Module
class QuizzDiModule(val context: Context) {

    @Provides
    fun providesQuizzApiClient(): QuizzApiClient {
        return QuizzApiClient()
    }

    @Provides
    fun provideQuizzServiceProvider(quizApiClient: QuizzApiClient): QuizzServiceProvider {
        return QuizzServiceProviderImpl(quizApiClient)
    }

    @Provides
    fun provideServiceLocator(quizzServiceProvider: QuizzServiceProvider, dbRepository: QuizzDbRepository): QuizzServiceLocator {
        return QuizzServiceLocatorImpl(quizzServiceProvider, dbRepository)
    }

    @Provides
    fun provideQuizzAttemptPresenter(serviceLocator: QuizzServiceLocator): QuizzAttemptContract.Presenter {
        return QuizzAttemptPresenter(serviceLocator)
    }

    @Provides
    fun provideQuizzDbRepository(context: Context): QuizzDbRepository {
        return QuizzDbRepository(context)
    }

    @Provides
    fun provideContext(): Context {
        return context
    }
}