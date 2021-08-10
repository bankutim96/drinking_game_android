package com.drinking.game.android.ui.login

import com.drinking.game.android.model.auth.TokenResponse

interface LoginScreen {
    fun handleSuccessLogin(token: TokenResponse)
    fun handleFailedLogin(message: String)
}