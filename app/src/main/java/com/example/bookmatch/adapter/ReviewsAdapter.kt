package com.example.bookmatch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bookmatch.R
import com.example.bookmatch.entity.Review
import com.example.bookmatch.ui.DeleteReviewDialog
import com.example.bookmatch.ui.EditReviewDialog
import com.google.android.material.button.MaterialButton

class ReviewsAdapter(context: Context?, var reviewsList: ArrayList<Review?>?, private val userEmail: String, private val emptyText: TextView) :
    ArrayAdapter<Review?>(context ?: throw NullPointerException("Context cannot be null!"),
        R.layout.reviews_item, reviewsList!!) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        val reviewsListItem = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.reviews_item, parent, false)
        }
        val reviewBookName = view.findViewById<TextView>(R.id.reviews_book_name)
        val reviewAuthorName = view.findViewById<TextView>(R.id.reviews_author_name)
        val reviewRating = view.findViewById<TextView>(R.id.reviews_rating)
        val reviewDeleteButton = view.findViewById<MaterialButton>(R.id.reviews_book_delete)
        reviewBookName.text = reviewsListItem!!.getBookItem().getBookName()
        reviewAuthorName.text = reviewsListItem.getBookItem().getAuthorName()
        reviewRating.text = if (reviewsListItem.getRating() != null) reviewsListItem.getRating().toString() else ""
        reviewDeleteButton.setOnClickListener {
            val logoutDialog = DeleteReviewDialog(
                context,
                R.style.DialogTheme,
                reviewsList!!.size == 1,
                userEmail,
                reviewsListItem,
                this@ReviewsAdapter,
                emptyText
            )
            logoutDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            logoutDialog.show()
        }
        view.setOnClickListener {
            val dialog = EditReviewDialog(context, R.style.DialogTheme,
                reviewsList!!.size == 1, userEmail, reviewsList!![position]!!, position, this, emptyText)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.show()
        }
        return view
    }

}