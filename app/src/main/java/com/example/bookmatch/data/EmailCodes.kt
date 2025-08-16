package com.example.bookmatch.data

import com.example.bookmatch.entity.EmailCode
import com.example.bookmatch.enums.EmailCodeType
import com.example.bookmatch.exception.EmailCodeNotFoundException

class EmailCodes {
    companion object {
        var emailCodeList: MutableList<EmailCode> = mutableListOf()

        fun getEmailCode(code: String, email: String, type: EmailCodeType): EmailCode {
            val emailCode: EmailCode? = emailCodeList.find { it.getCode() == code && it.getEmail() == email && it.getType() == type && it.valid }
            if (emailCode != null)
                return emailCode
            else
                throw EmailCodeNotFoundException()
        }
    }
}