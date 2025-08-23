package com.example.bookmatch.entity

import com.example.bookmatch.enums.ReviewsSort
import com.example.bookmatch.exception.BadArgumentException
import com.example.bookmatch.utils.EmailUtil
import java.util.UUID

class User(email: String, password: String) {
    private val id: String
    private val email: String
    private var password: String
    private var myList: MutableList<BookItem>
    private var reviewList: MutableList<Review>

    fun getEmail(): String {
        return email
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        validatePassword(password)
        this@User.password = password
    }

    fun getMyList(begin: Int, size: Int, sort: Boolean): MutableList<BookItem> {
        val sortedList = if (sort) {
            myList.sortedBy { it.getBookName() }
        }
        else {
            myList.sortedByDescending { it.getBookName() }
        }
        if (begin >= sortedList.size) {
            return mutableListOf()
        }
        var lastResult = begin + size
        if (lastResult >= sortedList.size) {
            lastResult = sortedList.size
        }
        return sortedList.subList(begin, lastResult).toMutableList()
    }

    fun addItemMyList(item: BookItem) {
        myList.add(item)
    }

    fun removeItemMyList(item: BookItem) {
        myList.remove(item)
    }

    fun getReviewList(begin: Int, size: Int, sort: ReviewsSort): MutableList<Review> {
        val sortedList = when (sort) {
            ReviewsSort.NAME_ASCENDING -> {
                reviewList.sortedBy { it.getBookItem().getBookName() }
            }
            ReviewsSort.NAME_DESCENDING -> {
                reviewList.sortedByDescending { it.getBookItem().getBookName() }
            }
            ReviewsSort.RATING_ASCENDING -> {
                reviewList.sortedBy { it.getRating() }
            }
            else -> {
                reviewList.sortedByDescending { it.getRating() }
            }
        }

        if (begin >= sortedList.size) {
            return mutableListOf()
        }
        var lastResult = begin + size
        if (lastResult >= sortedList.size) {
            lastResult = sortedList.size
        }
        return sortedList.subList(begin, lastResult).toMutableList()
    }

    fun addItemReviewList(item: Review) {
        reviewList.add(item)
    }

    fun removeItemReviewList(item: Review) {
        reviewList.remove(item)
    }

    init {
        EmailUtil.validateEmail(email)
        validatePassword(password)
        this@User.id = UUID.randomUUID().toString()
        this@User.email = email
        this@User.password = password
        myList = mutableListOf()
        reviewList = mutableListOf()
    }

    private fun validatePassword(password: String) {
        if (password.length < 6) {
            throw BadArgumentException("The password must have at least 6 characters!")
        }
    }
}