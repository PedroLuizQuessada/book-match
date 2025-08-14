package com.example.bookmatch.data

import com.example.bookmatch.entity.User
import com.example.bookmatch.exception.UserNotFoundException

class Users {
    companion object {
        var userList: MutableList<User> = mutableListOf()

        fun getUser(email: String): User {
            try {
                val user: User = userList.first { it.email == email }
                return user
            }
            catch (_: NoSuchElementException) {
                throw UserNotFoundException()
            }
        }

        fun getUser(email: String, password: String): User {
            try {
                val user: User = userList.first { it.email == email && it.password == password }
                return user
            }
            catch (_: NoSuchElementException) {
                throw UserNotFoundException()
            }
        }
    }
}