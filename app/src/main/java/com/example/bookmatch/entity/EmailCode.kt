package com.example.bookmatch.entity

import com.example.bookmatch.enums.EmailCodeType
import com.example.bookmatch.utils.EmailUtil
import java.util.UUID
import kotlin.random.Random

class EmailCode(email: String, type: EmailCodeType) {

    private val id: String
    private var code: String

    private var email: String

    private var type: EmailCodeType

    var valid: Boolean

    fun getCode(): String {
        return code
    }

    fun getEmail(): String {
        return email
    }

    fun getType(): EmailCodeType {
        return type
    }

    init {
        EmailUtil.validateEmail(email)
        this@EmailCode.id = UUID.randomUUID().toString()
        code = generateCode()
        this@EmailCode.email = email
        this@EmailCode.type = type
        valid = true
    }

    private fun generateCode(): String {
        val num1 = Random.nextInt(10).toString()
        val num2 = Random.nextInt(10).toString()
        val num3 = Random.nextInt(10).toString()
        val num4 = Random.nextInt(10).toString()
        val num5 = Random.nextInt(10).toString()
        val num6 = Random.nextInt(10).toString()

        val code: String = num1 + num2 + num3 + num4 + num5 + num6
        println("GENERATED CODE: $code")
        return code
    }

}