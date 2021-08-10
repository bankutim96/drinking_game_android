package com.drinking.game.android.ui.register

interface RegisterScreen {
    fun handleSuccessRegister()
    fun handleFailedRegister(message: String)
}