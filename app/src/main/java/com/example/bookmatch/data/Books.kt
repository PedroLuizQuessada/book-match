package com.example.bookmatch.data

import com.example.bookmatch.entity.Book

class Books {

    companion object {
        val bookList = listOf(
            Book("Livro A", "Sinopse livro A"),
            Book("Livro B", "Sinopse livro B"),
            Book("Livro C", "Sinopse livro C"),
            Book("Livro D", "Sinopse livro D"),
            Book("Livro E", "Sinopse livro E"),
            Book("Livro F", "Sinopse livro F"),
            Book("Livro G", "Sinopse livro G"),
            Book("Livro H", "Sinopse livro H"),
            Book("Livro I", "Sinopse livro I"),
            Book("Livro J", "Sinopse livro J")
            )

        fun getRandomBook(): Book {
            return bookList.random()
        }
    }
}