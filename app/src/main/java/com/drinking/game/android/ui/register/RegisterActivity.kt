package com.drinking.game.android.ui.register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.drinking.game.android.R
import com.drinking.game.android.injector
import com.drinking.game.android.model.auth.RegisterRequest
import com.drinking.game.android.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(), RegisterScreen {

    @Inject
    lateinit var registerPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        injector.inject(this)

        btnRegister.setOnClickListener {
            var hasError = false

            if(TextUtils.isEmpty(etUsername.text)) {
                tilUsername.error = "Username must not be empty"
                tilUsername.isErrorEnabled = true
                hasError = true
            }

            if(TextUtils.isEmpty(etEmail.text)) {
                tilEmail.error = "Email must not be empty"
                tilEmail.isErrorEnabled = true
                hasError = true
            }

            if(TextUtils.isEmpty(etPassword.text)) {
                tilPassword.error = "Password must not be empty"
                tilPassword.isErrorEnabled = true
                hasError = true
            } else if(!etPassword.text.equals(etRepeatPassword.text)) {
                tilRepeatPassword.error = "Repeat password is not the same"
                tilRepeatPassword.isErrorEnabled = true
                hasError = true
            }

            if(!hasError) {
                val username = etUsername.text?.toString()!!
                val email = etUsername.text?.toString()!!
                val password = etPassword.text?.toString()!!
                val dateOfBirth = etDateOfBirth.text?.toString()

                registerPresenter.register(RegisterRequest(username, email, password, dateOfBirth))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        registerPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        registerPresenter.detachScreen()
    }


    override fun handleSuccessRegister() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun handleFailedRegister(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}