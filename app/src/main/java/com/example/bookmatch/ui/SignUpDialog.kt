package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.entity.User

class SignUpDialog(context: Context, themeResId: Int, private val users: MutableList<User> = mutableListOf()) : Dialog(context, themeResId) {

    private var signUpText: TextView
    private var emailSignUp: EditText
    private var passwordSignUp: EditText
    private var passwordConfirmationSignUp: EditText
    private var createAccount: Button

    init {
        setContentView(R.layout.dialog_sign_up_change_password)

        signUpText = findViewById(R.id.sign_up_change_password_text)
        signUpText.setText(R.string.sign_up_text)

        emailSignUp = findViewById(R.id.sign_up_change_password_email)

        passwordSignUp = findViewById(R.id.sign_up_change_password_password)
        passwordSignUp.setHint(R.string.password_field_hint)

        passwordConfirmationSignUp = findViewById(R.id.sign_up_change_password_password_confirmation)
        passwordConfirmationSignUp.setHint(R.string.password_confirmation_field_hint)

        createAccount = findViewById(R.id.sign_up_change_password_button)
        createAccount.setText(R.string.create_account_button_text)

        createAccount.setOnClickListener {
            if (passwordSignUp.text.toString() != passwordConfirmationSignUp.text.toString()) {
                Toast.makeText(context, "The password fields must have the same value!", Toast.LENGTH_SHORT).show()
            }

            else {

                try {
                    val user = User(emailSignUp.text.toString(), passwordSignUp.text.toString())

                    dismiss()

                    val emailCodeDialog = EmailCodeDialog(context, R.style.DialogTheme, users,
                        user, "Account created successfully")
                    emailCodeDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    emailCodeDialog.show()
                }
                catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}