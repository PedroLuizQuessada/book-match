package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.entity.User

class EmailCodeDialog(context: Context,
                      themeResId: Int,
                      private val users: MutableList<User> = mutableListOf(),
                      user: User,
                      successMessage: String) : Dialog(context, themeResId) {

    private var emailCode: EditText
    private var confirmCode: Button

    init {
        setContentView(R.layout.dialog_email_code)
        emailCode = findViewById(R.id.email_code)
        confirmCode = findViewById(R.id.confirm_code)

        confirmCode.setOnClickListener {
            if (emailCode.text.toString() == "abc123") {
                users.add(user)
                dismiss()
                val intent = Intent(context, Home::class.java)
                context.startActivity(intent)
                Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "The code is incorrect!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}