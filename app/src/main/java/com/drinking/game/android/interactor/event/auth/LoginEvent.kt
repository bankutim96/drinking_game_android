package com.drinking.game.android.interactor.event.auth

import com.drinking.game.android.model.auth.TokenResponse

data class LoginEvent(
    var code: Int = 0,
    var token: TokenResponse? = null,
    var throwable: Throwable? = null
)