package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.adapter.MyListAdapter
import com.example.bookmatch.data.Users
import com.example.bookmatch.entity.BookItem

class DeleteMyListDialog(context: Context,
                         themeResId: Int,
                         singleItem: Boolean,
                         userEmail: String,
                         myListItem: BookItem,
                         myListAdapter: MyListAdapter,
                         emptyText: TextView): Dialog(context, themeResId) {

    private var confirmDeleteMyList: Button
    private var denyDeleteMyList: Button

    init {
        setContentView(R.layout.dialog_delete_my_list)
        confirmDeleteMyList = findViewById(R.id.confirm_delete_my_list)
        denyDeleteMyList = findViewById(R.id.deny_delete_my_list)

        confirmDeleteMyList.setOnClickListener {
            Users.getUser(userEmail).removeItemMyList(myListItem)
            myListAdapter.myList!!.remove(myListItem)
            myListAdapter.notifyDataSetChanged()
            if (singleItem) {
                emptyText.visibility = View.VISIBLE
            } else {
                emptyText.visibility = View.GONE
            }
            dismiss()
            Toast.makeText(context, R.string.book_removed, Toast.LENGTH_SHORT).show()
        }

        denyDeleteMyList.setOnClickListener {
            dismiss()
        }
    }

}