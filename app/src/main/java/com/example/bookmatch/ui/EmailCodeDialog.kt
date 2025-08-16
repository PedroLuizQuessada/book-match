package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.data.EmailCodes
import com.example.bookmatch.data.Users
import com.example.bookmatch.entity.EmailCode
import com.example.bookmatch.entity.User
import com.example.bookmatch.enums.EmailCodeType

class EmailCodeDialog(
    context: Context,
    themeResId: Int,
    user: User,
    emailCodeType: EmailCodeType
) : Dialog(context, themeResId) {

    private var emailCodeField: EditText
    private var confirmCode: Button

    init {
        setContentView(R.layout.dialog_email_code)
        emailCodeField = findViewById(R.id.email_code)
        confirmCode = findViewById(R.id.confirm_code)

        confirmCode.setOnClickListener {

            try {
                var emailCode: EmailCode = EmailCodes.getEmailCode(emailCodeField.text.toString(), user.getEmail(), emailCodeType)

                if (emailCodeType == EmailCodeType.SIGN_UP)
                    Users.userList.add(user)
                else if (emailCodeType == EmailCodeType.CHANGE_PASSWORD) {
                    try {
                        val updateUser = Users.getUser(user.getEmail())
                        updateUser.setPassword(user.getPassword())
                    }
                    catch (e: Exception) {
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
                emailCode.valid = false
                dismiss()

                val intent = Intent(context, Home::class.java)
                context.startActivity(intent)
                Toast.makeText(context, emailCodeType.successMessage, Toast.LENGTH_SHORT).show()
            }
            catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}