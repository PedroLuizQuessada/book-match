package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.data.BookItems
import com.example.bookmatch.data.Users
import com.example.bookmatch.entity.BookItem
import com.example.bookmatch.entity.Review
import com.example.bookmatch.exception.BookItemNotFoundException

class AddReviewDialog(context: Context, themeResId: Int, userEmail: String, book: String, author: String, exploreFragment: ExploreFragment) : Dialog(context, themeResId) {

    private var addReviewText: TextView
    private var bookNameField: EditText
    private var authorNameField: EditText
    private var bookReviewField: EditText
    private var bookRatingField: EditText
    private var addReview: Button

    init {
        setContentView(R.layout.dialog_review)

        addReviewText = findViewById(R.id.review_text)
        addReviewText.setText(R.string.add_review_text)

        bookNameField = findViewById(R.id.review_book)
        bookNameField.setText(book)

        authorNameField = findViewById(R.id.review_author)
        authorNameField.setText(author)

        bookReviewField = findViewById(R.id.review)

        bookRatingField = findViewById(R.id.review_rating)

        addReview = findViewById(R.id.save_review)

        addReview.setOnClickListener {

            var bookItem: BookItem
            try {
                bookItem = BookItems.getBookItem(bookNameField.text.toString(), authorNameField.text.toString())
            }
            catch (_: BookItemNotFoundException) {
                bookItem = BookItem(bookNameField.text.toString(), authorNameField.text.toString())
                BookItems.bookItemList.add(bookItem)
            }

            val rating = bookRatingField.text.toString().takeIf { it.isNotBlank() }?.toInt()

            Users.getUser(userEmail).addItemReviewList(Review(bookItem, bookReviewField.text.toString(), rating))
            exploreFragment.loadBookData()
            dismiss()
            Toast.makeText(context, R.string.review_added, Toast.LENGTH_SHORT).show()
        }
    }

}