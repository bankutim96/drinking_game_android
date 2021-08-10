package com.drinking.game.android.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.drinking.game.android.R
import com.drinking.game.android.injector
import com.drinking.game.android.model.auth.TokenResponse
import com.drinking.game.android.ui.register.RegisterActivity
import com.drinking.game.android.util.SessionManager
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity: AppCompatActivity(), LoginScreen {

    @Inject
    lateinit var loginPresenter: LoginPresenter

    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        injector.inject(this)
        sessionManager = SessionManager(this)

        btnLogin.setOnClickListener {
            var hasError = false
            if(TextUtils.isEmpty(etUsername.text)) {
                tilUsername.error = "Username must not be empty"
                tilUsername.isErrorEnabled = true
                hasError = true
            }

            if(TextUtils.isEmpty(etPassword.text)) {
                tilPassword.error = "Password must not be empty"
                tilPassword.isErrorEnabled = true
                hasError = true
            }

            if(!hasError) {
                loginPresenter.login(etUsername.text?.toString()!!, etPassword.text?.toString()!!)
            }
        }
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        loginPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        loginPresenter.detachScreen()
    }

    override fun handleSuccessLogin(token: TokenResponse) {
        sessionManager.saveTokens(token.accessToken!!, token.refreshToken!!)
        Toast.makeText(this, sessionManager.getAccessToken(), Toast.LENGTH_LONG).show()
    }

    override fun handleFailedLogin(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}