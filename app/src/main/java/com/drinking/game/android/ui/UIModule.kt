package com.drinking.game.android.ui

import android.content.Context
import com.drinking.game.android.interactor.AuthInteractor
import com.drinking.game.android.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun loginPresenter(executor: Executor, authInteractor: AuthInteractor) = LoginPresenter(executor, authInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}