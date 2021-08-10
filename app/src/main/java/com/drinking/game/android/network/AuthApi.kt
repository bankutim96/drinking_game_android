package com.drinking.game.android.network

import com.drinking.game.android.model.auth.RegisterRequest
import com.drinking.game.android.model.auth.RegisterResponse
import com.drinking.game.android.model.auth.TokenRequest
import com.drinking.game.android.model.auth.TokenResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    fun login(@Body login: TokenRequest) : Call<TokenResponse>

    @POST("register")
    fun register(@Body register: RegisterRequest): Call<RegisterResponse>
}