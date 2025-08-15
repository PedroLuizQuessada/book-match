package com.example.bookmatch.entity

import com.example.bookmatch.enums.EmailCodeType
import com.example.bookmatch.exception.BadArgumentException
import com.example.bookmatch.utils.EmailUtil

class EmailCode(code: String, email: String, type: EmailCodeType) {

    private var _code: String

    private var _email: String

    private var _type: EmailCodeType

    val code: String
        get() = _code

    val email: String
        get() = _email

    val type: EmailCodeType
        get() = _type

    var valid: Boolean = true

    init {
        validateCode(code)
        EmailUtil.validateEmail(email)
        _code = code
        _email = email
        _type = type
    }

    private fun validateCode(code: String) {
        if (code.length < 6) {
            throw BadArgumentException("The e-mail code must have at least 6 characters!")
        }
    }

}