package com.example.baseapp.common

object ResponseCode {
    const val ERROR = "0"
    const val SUCCESS = "1"
    const val AUTH_ERROR = "2"
    const val OTP_INVALID = "3"
    const val NOT_FOUND = "4"
    const val WARNING = "5"
    const val TOKEN_EXPIRED = "6"
    const val TOKEN_INVALID = "7"
    const val EXCEPTION = "9999"
}

object ErrorCode {
    const val API_ERROR = 1 // Api call error (response code is different 200)
    const val FIREBASE_INSTANCE_TOKEN_NULL = 2
    const val DEVICE_TOKEN_NULL = 3
    const val GET_CALL_LOG = 4
}