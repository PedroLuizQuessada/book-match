package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.data.Reviews
import com.example.bookmatch.entity.Review

class AddReviewDialog(context: Context, themeResId: Int, book: String, exploreFragment: ExploreFragment) : Dialog(context, themeResId) {

    private var addReviewText: TextView
    private var bookNameField: EditText
    private var bookReviewField: EditText
    private var bookRatingField: EditText
    private var addReview: Button

    init {
        setContentView(R.layout.dialog_review)

        addReviewText = findViewById(R.id.review_text)
        addReviewText.setText(R.string.add_review_text)

        bookNameField = findViewById(R.id.review_book)
        bookNameField.setText(book)

        bookReviewField = findViewById(R.id.review)

        bookNameField = findViewById(R.id.review_book)

        bookRatingField = findViewById(R.id.review_rating)

        addReview = findViewById(R.id.save_review)

        addReview.setOnClickListener {

            val rating = bookRatingField.text.toString().takeIf { it.isNotBlank() }?.toInt()

            Reviews.reviewList.add(Review(bookNameField.text.toString(),
                bookReviewField.text.toString(), rating))
            exploreFragment.loadBookData()
            dismiss()
            Toast.makeText(context, R.string.review_added, Toast.LENGTH_SHORT).show()
        }
    }

}