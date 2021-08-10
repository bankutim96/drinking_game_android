package com.drinking.game.android.model.auth

import java.util.*

class RegisterRequest (
    var username: String,
    var email: String,
    var password: String,
    var dateOfBirth: String?
)