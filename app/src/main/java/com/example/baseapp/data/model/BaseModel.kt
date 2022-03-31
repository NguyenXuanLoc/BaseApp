package com.example.baseapp.data.model

import com.example.baseapp.common.ResponseCode
import com.google.gson.Gson
import java.io.Serializable

open class BaseModel : Serializable {
    var code: String? = null
    var message: String = ""

    fun responseIsSuccess(): Boolean {
        return code == ResponseCode.SUCCESS
    }

    fun notFound(): Boolean {
        return code == ResponseCode.NOT_FOUND
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}