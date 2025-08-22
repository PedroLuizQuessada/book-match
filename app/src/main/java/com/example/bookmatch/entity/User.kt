package com.example.bookmatch.entity

import com.example.bookmatch.exception.BadArgumentException
import com.example.bookmatch.utils.EmailUtil

class User(email: String, password: String) {
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

    fun getReviewList(): MutableList<Review> {
        return reviewList
    }

    init {
        EmailUtil.validateEmail(email)
        validatePassword(password)
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