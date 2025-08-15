package com.example.bookmatch.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.data.Users
import androidx.core.content.edit

class Login : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var rememberMe: CheckBox
    private lateinit var loginButton: Button
    private lateinit var signUpLink: TextView
    private lateinit var forgotPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val preferences = getSharedPreferences("remember_me", MODE_PRIVATE)
        if (preferences.contains("e_mail")) {
            redirect()
        }

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        rememberMe = findViewById(R.id.remember_me)
        loginButton = findViewById(R.id.login_button)
        signUpLink = findViewById(R.id.signup)
        forgotPassword = findViewById(R.id.forgot_password)

        loginButton.setOnClickListener {
            try {
                Users.getUser(email.text.toString(), password.text.toString())
                configureRememberMe()
                redirect()
            }
            catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        signUpLink.setOnClickListener {
            val signUpDialog = SignUpDialog(this, R.style.DialogTheme)
            signUpDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            signUpDialog.show()
        }

        forgotPassword.setOnClickListener {
            val forgotPasswordDialog = ForgotPasswordDialog(this, R.style.DialogTheme)
            forgotPasswordDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            forgotPasswordDialog.show()
        }
    }

    private fun redirect() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

    private fun configureRememberMe() {
        val preferences = getSharedPreferences("remember_me", MODE_PRIVATE)
        preferences.edit {
            if (rememberMe.isChecked) {
                putString("e_mail", email.text.toString())
            } else {
                remove("e_mail")
            }

        }
    }
}