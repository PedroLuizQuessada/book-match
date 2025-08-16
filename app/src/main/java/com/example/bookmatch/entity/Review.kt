package com.example.bookmatch.entity

import com.example.bookmatch.exception.BadArgumentException

class Review(bookName: String, review: String, rating: Int?) {

    private var bookName: String
    private var review: String
    private var rating: Int?

    init {
        validateBookName(bookName)
        this@Review.bookName = bookName
        this@Review.review = review
        this@Review.rating = rating
    }

    private fun validateBookName(bookName: String) {
        if (bookName.isEmpty()) {
            throw BadArgumentException("The review must have a book name!")
        }
    }
}