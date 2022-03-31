package com.example.baseapp.ui.base

import com.example.baseapp.common.ErrorCode
import com.example.baseapp.data.model.BaseModel


interface BaseView {

    fun onApiCallError(e: Throwable? = null, code: Int = ErrorCode.API_ERROR) {}

    fun onApiResponseError(error: BaseModel) {}

    fun onNetworkError() {}

    fun getExtrasValue() {}

    fun dismissRefreshIcon() {}
}