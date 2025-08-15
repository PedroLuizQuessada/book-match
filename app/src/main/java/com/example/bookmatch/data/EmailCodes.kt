package com.example.bookmatch.data

import com.example.bookmatch.entity.EmailCode
import com.example.bookmatch.enums.EmailCodeType
import com.example.bookmatch.exception.EmailCodeNotFoundException

class EmailCodes {
    companion object {
        var emailCodeList: MutableList<EmailCode> = mutableListOf()

        fun getEmailCode(code: String, email: String, type: EmailCodeType): EmailCode {
            try {
                val emailCode: EmailCode = emailCodeList.first { it.code == code && it.email == email && it.type == type && it.valid }
                return emailCode
            }
            catch (_: NoSuchElementException) {
                throw EmailCodeNotFoundException()
            }
        }
    }
}