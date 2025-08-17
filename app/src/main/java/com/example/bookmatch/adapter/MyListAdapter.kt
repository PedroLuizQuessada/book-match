package com.example.bookmatch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.bookmatch.R
import com.example.bookmatch.data.Users
import com.google.android.material.button.MaterialButton

class MyListAdapter(context: Context?, var myList: ArrayList<String?>?, private var userEmail: String) :
    ArrayAdapter<String?>(context ?: throw NullPointerException("Context cannot be null!"),
        R.layout.my_list_item, myList!!) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        val myListItem = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.my_list_item, parent, false)
        }
        val myListCardView = view.findViewById<CardView>(R.id.my_list_card_view)
        val myListBook = view.findViewById<TextView>(R.id.my_list_book_name)
        val myListDeleteButton = view.findViewById<MaterialButton>(R.id.my_list_book_delete)
        myListBook.text = myListItem
        myListDeleteButton.setOnClickListener {
            Users.getUser(userEmail).removeItemMyList(myListItem.toString())
            myListCardView.visibility = View.GONE
            Toast.makeText(context, R.string.book_removed, Toast.LENGTH_SHORT).show()
        }
        return view
    }
}