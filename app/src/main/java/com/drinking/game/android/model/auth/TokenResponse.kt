package com.drinking.game.android.model.auth

import com.google.gson.annotations.SerializedName

data class TokenResponse (
    @SerializedName("accessToken")
    var accessToken: String? = null,
    @SerializedName("refreshToken")
    var refreshToken: String? = null
)