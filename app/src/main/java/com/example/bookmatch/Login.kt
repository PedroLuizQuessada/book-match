package com.example.bookmatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {
    val users: MutableMap<String, String> = HashMap()
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var signupLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        signupLink = findViewById(R.id.signup)

        loginButton.setOnClickListener {
            if (users.contains(email.text.toString()) && users[email.text.toString()] == password.text.toString()) {
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }

        signupLink.setOnClickListener {
            val signupDialog = SignUpDialog(this,R.style.DialogTheme, users)
            signupDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            signupDialog.show()
        }
    }
}