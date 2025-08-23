package com.example.bookmatch.entity

import java.util.UUID

class Review(private var bookItem: BookItem, private var review: String?, private var rating: Int?) {

    private val id: String = UUID.randomUUID().toString()

    fun getBookItem(): BookItem {
        return bookItem
    }

    fun getReview(): String? {
        return review
    }

    fun setReview(review: String?) {
        this@Review.review = review
    }

    fun getRating(): Int? {
        return rating
    }

    fun setRating(rating: Int?) {
        this@Review.rating = rating
    }
}