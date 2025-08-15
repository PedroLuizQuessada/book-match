package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.data.Users
import com.example.bookmatch.entity.User

class EmailCodeDialog(
    context: Context,
    themeResId: Int,
    user: User,
    successMessage: Int
) : Dialog(context, themeResId) {

    private var emailCode: EditText
    private var confirmCode: Button

    init {
        setContentView(R.layout.dialog_email_code)
        emailCode = findViewById(R.id.email_code)
        confirmCode = findViewById(R.id.confirm_code)

        confirmCode.setOnClickListener {
            if (emailCode.text.toString() == "abc123") { //TODO
                if (successMessage == R.string.account_created)
                    Users.userList.add(user)
                else if (successMessage == R.string.password_changed) {
                    try {
                        val updateUser = Users.getUser(user.email)
                        updateUser.password = user.password
                    }
                    catch (e: Exception) {
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
                dismiss()
                println("USUARIO ATUALIZADO: " + Users.getUser(user.email).password) //TODO
                val intent = Intent(context, Home::class.java)
                context.startActivity(intent)
                Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "The code is incorrect!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}