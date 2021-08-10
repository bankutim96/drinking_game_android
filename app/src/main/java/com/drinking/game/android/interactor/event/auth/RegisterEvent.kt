package com.drinking.game.android.interactor.event.auth

import com.drinking.game.android.model.auth.RegisterResponse
import com.drinking.game.android.model.auth.TokenResponse

data class RegisterEvent (
    var code: Int = 0,
    var user: RegisterResponse? = null,
    var throwable: Throwable? = null
)