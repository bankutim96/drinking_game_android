package com.drinking.game.android.model.auth

import com.google.gson.annotations.SerializedName
import java.util.*

data class RegisterResponse (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("role")
    var role: String? = null,
    @SerializedName("dateOfBirth")
    var dateOfBirth: Date? = null,
)