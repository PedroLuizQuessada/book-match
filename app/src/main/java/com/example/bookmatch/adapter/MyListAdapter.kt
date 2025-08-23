package com.example.bookmatch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bookmatch.R
import com.example.bookmatch.entity.BookItem
import com.example.bookmatch.ui.AddReviewDialog
import com.example.bookmatch.ui.DeleteMyListDialog
import com.google.android.material.button.MaterialButton

class MyListAdapter(context: Context?, var myList: ArrayList<BookItem?>?, private val userEmail: String, private val emptyText: TextView) :
    ArrayAdapter<BookItem?>(context ?: throw NullPointerException("Context cannot be null!"),
        R.layout.my_list_item, myList!!) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        val myListItem = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.my_list_item, parent, false)
        }
        val myListBookName = view.findViewById<TextView>(R.id.my_list_book_name)
        val myListAuthorName = view.findViewById<TextView>(R.id.my_list_author_name)
        val myListAddReviewButton = view.findViewById<MaterialButton>(R.id.my_list_book_review)
        val myListDeleteButton = view.findViewById<MaterialButton>(R.id.my_list_book_delete)
        myListBookName.text = myListItem!!.getBookName()
        myListAuthorName.text = myListItem.getAuthorName()
        myListAddReviewButton.setOnClickListener {
            val addReviewDialog = AddReviewDialog(context,
                R.style.DialogTheme,
                userEmail,
                myListItem.getBookName(),
                myListItem.getAuthorName(),
                null)
            addReviewDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            addReviewDialog.show()
        }
        myListDeleteButton.setOnClickListener {
            val logoutDialog = DeleteMyListDialog(context,
                R.style.DialogTheme,
                myList!!.size == 1,
                userEmail,
                myListItem,
                this@MyListAdapter,
                emptyText)
            logoutDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            logoutDialog.show()
        }
        return view
    }
}