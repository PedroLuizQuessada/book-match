package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.adapter.ReviewsAdapter
import com.example.bookmatch.data.Users
import com.example.bookmatch.entity.Review

class EditReviewDialog(context: Context,
                       themeResId: Int,
                       singleItem: Boolean,
                       userEmail: String,
                       review: Review,
                       position: Int,
                       reviewsAdapter: ReviewsAdapter,
                       emptyText: TextView) : Dialog(context, themeResId) {

    private var editReviewText: TextView
    private var bookNameField: EditText
    private var authorNameField: EditText
    private var bookReviewField: EditText
    private var bookRatingField: EditText
    private var editReview: Button
    private var deleteReview: Button

    init {
        setContentView(R.layout.dialog_review)

        editReviewText = findViewById(R.id.review_text)
        editReviewText.setText(R.string.edit_review_text)

        bookNameField = findViewById(R.id.review_book)
        bookNameField.setText(review.getBookItem().getBookName())

        authorNameField = findViewById(R.id.review_author)
        authorNameField.setText(review.getBookItem().getAuthorName())

        bookReviewField = findViewById(R.id.review)
        if (review.getReview() != null) {
            bookReviewField.setText(review.getReview())
        }

        bookRatingField = findViewById(R.id.review_rating)
        if (review.getRating() != null) {
            bookRatingField.setText(review.getRating().toString())
        }

        editReview = findViewById(R.id.save_review)
        editReview.setOnClickListener {
            reviewsAdapter.reviewsList!![position].let( { review ->
                review!!.setReview(bookReviewField.text.toString())
                review.setRating(bookRatingField.text.toString().toIntOrNull())
            }
            )
            reviewsAdapter.notifyDataSetChanged()
            dismiss()
            Toast.makeText(context, R.string.review_edited, Toast.LENGTH_SHORT).show()
        }

        deleteReview = findViewById(R.id.delete_review)
        deleteReview.visibility = View.VISIBLE
        deleteReview.setOnClickListener {
            Users.getUser(userEmail).removeItemReviewList(review)
            reviewsAdapter.reviewsList!!.remove(review)
            reviewsAdapter.notifyDataSetChanged()
            if (singleItem) {
                emptyText.visibility = View.VISIBLE
            } else {
                emptyText.visibility = View.GONE
            }
            dismiss()
            Toast.makeText(context, R.string.review_removed, Toast.LENGTH_SHORT).show()
        }
    }

}