package com.example.bookmatch.data

import com.example.bookmatch.entity.Book

class Books {

    companion object {
        val bookList = listOf(
            Book("Livro 1", "Sinopse livro 1"),
            Book("Livro 2", "Sinopse livro 2"),
            Book("Livro 3", "Sinopse livro 3"),
            Book("Livro 4", "Sinopse livro 4"),
            Book("Livro 5", "Sinopse livro 5"),
            Book("Livro 6", "Sinopse livro 6"),
            Book("Livro 7", "Sinopse livro 7"),
            Book("Livro 8", "Sinopse livro 8"),
            Book("Livro 9", "Sinopse livro 9"),
            Book("Livro 10", "Sinopse livro 10")
            )

        fun getRandomBook(): Book {
            return bookList.random()
        }
    }
}