package com.drinking.game.android.interactor

import com.drinking.game.android.network.AuthApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideAuthInteractor(authApi: AuthApi) = AuthInteractor(authApi)
}