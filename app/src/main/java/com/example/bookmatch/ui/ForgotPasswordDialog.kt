package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.entity.User

class ForgotPasswordDialog(context: Context, themeResId: Int, private val users: MutableList<User> = mutableListOf()) : Dialog(context, themeResId) {

    private var changePasswordText: TextView
    private var emailChangePassword: EditText
    private var newPasswordChangePassword: EditText
    private var newPasswordConfirmationChangePassword: EditText
    private var changePassword: Button

    init {
        setContentView(R.layout.dialog_sign_up_change_password)

        changePasswordText = findViewById(R.id.sign_up_change_password_text)
        changePasswordText.setText(R.string.change_password_text)

        emailChangePassword = findViewById(R.id.sign_up_change_password_email)

        newPasswordChangePassword = findViewById(R.id.sign_up_change_password_password)
        newPasswordChangePassword.setHint(R.string.new_password_field_hint)

        newPasswordConfirmationChangePassword = findViewById(R.id.sign_up_change_password_password_confirmation)
        newPasswordConfirmationChangePassword.setHint(R.string.new_password_confirmation_field_hint)

        changePassword = findViewById(R.id.sign_up_change_password_button)
        changePassword.setText(R.string.change_password_button_text)

        changePassword.setOnClickListener {

            if (newPasswordChangePassword.text.toString() != newPasswordConfirmationChangePassword.text.toString()) {
                Toast.makeText(context, "The password fields must have the same value!", Toast.LENGTH_SHORT).show()
            }

            else {

                try {
                    val user = User(emailChangePassword.text.toString(), newPasswordChangePassword.text.toString())

                    dismiss()

                    val emailCodeDialog = EmailCodeDialog(context, R.style.DialogTheme, users, user,
                        "Password changed successfully")
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