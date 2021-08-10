package com.drinking.game.android

import com.drinking.game.android.interactor.InteractorModule
import com.drinking.game.android.network.NetworkModule
import com.drinking.game.android.ui.UIModule
import com.drinking.game.android.ui.login.LoginActivity
import com.drinking.game.android.ui.register.RegisterActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class])
interface DrinkingGameApplicationComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(registerActivity: RegisterActivity)
}