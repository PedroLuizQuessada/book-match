package com.example.bookmatch

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpEmailCodeDialog(context: Context,
                            themeResId: Int,
                            private val users: MutableMap<String, String>,
                            email: String,
                            password: String) : Dialog(context, themeResId) {

    private var emailSignUpCode: EditText
    private var confirmCode: Button

    init {
        setContentView(R.layout.dialog_signup_email_code)
        emailSignUpCode = findViewById(R.id.sign_up_email_code)
        confirmCode = findViewById(R.id.confirm_code)

        confirmCode.setOnClickListener {
            if (emailSignUpCode.text.toString() == "abc123") {
                users.put(email, password)
                dismiss()
                val intent = Intent(context, Home::class.java)
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "The code is incorrect!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}