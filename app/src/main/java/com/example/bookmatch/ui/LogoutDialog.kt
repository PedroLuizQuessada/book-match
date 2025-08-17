package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.widget.Button
import com.example.bookmatch.R
import com.example.bookmatch.utils.SharedPreferencesUtil

class LogoutDialog(context: Context, themeResId: Int): Dialog(context, themeResId) {

    private var confirmLogout: Button
    private var denyLogout: Button

    init {
        setContentView(R.layout.dialog_logout)
        confirmLogout = findViewById(R.id.confirm_logout)
        denyLogout = findViewById(R.id.deny_logout)

        confirmLogout.setOnClickListener {
            SharedPreferencesUtil.removeKey(context,"remember_me", "e_mail")
            val intent = Intent(context, Login::class.java)
            context.startActivity(intent)
        }

        denyLogout.setOnClickListener {
            dismiss()
        }
    }

}