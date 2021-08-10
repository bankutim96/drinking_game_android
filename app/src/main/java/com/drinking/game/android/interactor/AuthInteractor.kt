package com.drinking.game.android.interactor

import com.drinking.game.android.interactor.event.auth.LoginEvent
import com.drinking.game.android.interactor.event.auth.RegisterEvent
import com.drinking.game.android.model.auth.RegisterRequest
import com.drinking.game.android.model.auth.TokenRequest
import com.drinking.game.android.network.AuthApi
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class AuthInteractor @Inject constructor(private var authApi: AuthApi) {

    fun login(username: String, password: String) {
        val loginCall = authApi.login(TokenRequest(username, password))
        val event = LoginEvent()

        try {
            val tokenResponse = loginCall.execute();
            if(tokenResponse.code() == 401) {
                throw Exception("Invalid username or password!")
            } else if (tokenResponse.code() != 200) {
                throw Exception("Internal server error!")
            }

            event.code = tokenResponse.code()
            event.token = tokenResponse.body()
            EventBus.getDefault().post(event)
        } catch (ex: Exception) {
            event.throwable = ex
            EventBus.getDefault().post(event)
        }
    }

    fun register(register: RegisterRequest) {
        val registerCall = authApi.register(register)
        val event = RegisterEvent()

        try {
            val registerResponse = registerCall.execute()
            if(registerResponse.code() == 409) {
                throw Exception("There is already a user with the given email or username!")
            } else if (registerResponse.code() != 201) {
                throw Exception("Internal server error!")
            }

            event.code = registerResponse.code()
            event.user = registerResponse.body()
            EventBus.getDefault().post(event)
        } catch (ex: Exception) {
            event.throwable = ex
            EventBus.getDefault().post(event)
        }
    }
}