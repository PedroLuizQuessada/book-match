package com.example.bookmatch.data

import com.example.bookmatch.entity.User
import com.example.bookmatch.exception.UserNotFoundException

class Users {
    companion object {
        var userList: MutableList<User> = mutableListOf()

        fun getUser(email: String): User {
            val user: User? = userList.find { it.getEmail() == email }
            if (user != null)
                return user
            else
                throw UserNotFoundException()
        }

        fun getUser(email: String, password: String): User {
            val user: User? = userList.find { it.getEmail() == email && it.getPassword() == password }
            if (user != null)
                return user
            else
                throw UserNotFoundException()
        }
    }
}