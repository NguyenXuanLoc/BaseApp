package com.example.baseapp.data.mapper

import com.example.baseapp.data.model.BaseModel
import com.example.baseapp.data.response.BaseResponse


fun <T> BaseResponse<T>.convertToModel(): BaseModel {
    val response = this
    return BaseModel().apply {
        code = response.code
        message = response.message ?: ""
    }
}