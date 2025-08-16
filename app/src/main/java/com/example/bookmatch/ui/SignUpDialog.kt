package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.data.EmailCodes
import com.example.bookmatch.data.Users
import com.example.bookmatch.entity.EmailCode
import com.example.bookmatch.entity.User
import com.example.bookmatch.enums.EmailCodeType
import com.example.bookmatch.exception.EmailAlreadyInUseException
import com.example.bookmatch.exception.PasswordFieldsWithDifferentValuesException
import com.example.bookmatch.exception.UserNotFoundException

class SignUpDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {

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

            try {
                if (passwordSignUp.text.toString() != passwordConfirmationSignUp.text.toString()) {
                    throw PasswordFieldsWithDifferentValuesException()
                }

                val user = User(emailSignUp.text.toString(), passwordSignUp.text.toString())

                try {
                    Users.getUser(user.getEmail())
                    throw EmailAlreadyInUseException()
                }
                catch (_: UserNotFoundException) {}

                EmailCodes.emailCodeList.add(EmailCode(user.getEmail(), EmailCodeType.SIGN_UP))

                dismiss()

                val emailCodeDialog = EmailCodeDialog(context, R.style.DialogTheme,
                    user, EmailCodeType.SIGN_UP)
                emailCodeDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                emailCodeDialog.show()
            }
            catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}