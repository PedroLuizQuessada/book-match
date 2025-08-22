package com.example.bookmatch.data

import com.example.bookmatch.entity.Book

class Books {

    companion object {
        val bookList = listOf(
            Book("Livro A", "Autor A", "Sinopse livro A"),
            Book("Livro B", "Autor B", "Sinopse livro B"),
            Book("Livro C", "Autor C", "Sinopse livro C"),
            Book("Livro D", "Autor D", "Sinopse livro D"),
            Book("Livro E", "Autor E", "Sinopse livro E"),
            Book("Livro F", "Autor F", "Sinopse livro F"),
            Book("Livro G", "Autor G", "Sinopse livro G"),
            Book("Livro H", "Autor H", "Sinopse livro H"),
            Book("Livro I", "Autor I", "Sinopse livro I"),
            Book("Livro J", "Autor J", "Sinopse livro J")
            )

        fun getRandomBook(): Book {
            return bookList.random()
        }
    }
}