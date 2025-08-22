package com.example.bookmatch.data

import com.example.bookmatch.entity.BookItem
import com.example.bookmatch.exception.BookItemNotFoundException

class BookItems {
    companion object {
        var bookItemList: MutableList<BookItem> = mutableListOf()

        fun getBookItem(bookName: String, authorName: String): BookItem {
            val bookItem: BookItem? = bookItemList.find { it.getBookName() == bookName && it.getAuthorName() == authorName }
            if (bookItem != null)
                return bookItem
            else
                throw BookItemNotFoundException()
        }
    }
}