package com.example.bookmatch.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.entity.User

class Login : AppCompatActivity() {
    val users: MutableList<User> = mutableListOf()
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpLink: TextView
    private lateinit var forgotPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.login_button)
        signUpLink = findViewById(R.id.signup)
        forgotPassword = findViewById(R.id.forgot_password)

        loginButton.setOnClickListener {
            if (users.any { it.email == email.text.toString() && it.password == password.text.toString() }) {
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }

        signUpLink.setOnClickListener {
            val signUpDialog = SignUpDialog(this, R.style.DialogTheme, users)
            signUpDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            signUpDialog.show()
        }

        forgotPassword.setOnClickListener {
            val forgotPasswordDialog = ForgotPasswordDialog(this, R.style.DialogTheme, users)
            forgotPasswordDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            forgotPasswordDialog.show()
        }
    }
}