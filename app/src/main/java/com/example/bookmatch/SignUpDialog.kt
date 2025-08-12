package com.example.bookmatch

import android.app.Dialog
import android.content.Context
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpDialog(context: Context, themeResId: Int, private val users: MutableMap<String, String>) : Dialog(context, themeResId) {

    private var emailSignUp: EditText
    private var passwordSignUp: EditText
    private var passwordConfirmationSignUp: EditText
    private var createAccount: Button

    init {
        setContentView(R.layout.dialog_signup)
        emailSignUp = findViewById(R.id.sign_up_email)
        passwordSignUp = findViewById(R.id.sign_up_password)
        passwordConfirmationSignUp = findViewById(R.id.sign_up_password_confirmation)
        createAccount = findViewById(R.id.create_account)

        createAccount.setOnClickListener {
            if (validateEmail(emailSignUp.text.toString()) &&
                    validatePassword(passwordSignUp.text.toString(), passwordConfirmationSignUp.text.toString())) {
                dismiss()

                val signUpEmailCodeDialog = SignUpEmailCodeDialog(context,R.style.DialogTheme, users, emailSignUp.text.toString(), passwordSignUp.text.toString())
                signUpEmailCodeDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                signUpEmailCodeDialog.show()
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "Invalid e-mail!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun validatePassword(password: String, passwordConfirmation: String): Boolean {
        if (password != passwordConfirmation) {
            Toast.makeText(context, "The password fields must have the same value!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(context, "The password fields must have at least 6 characters!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}