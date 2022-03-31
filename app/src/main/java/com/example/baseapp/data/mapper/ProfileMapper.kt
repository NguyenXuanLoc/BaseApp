package com.example.baseapp.data.mapper

import com.example.baseapp.data.model.ProfileModel
import com.example.baseapp.data.response.ProfileResponse

fun ProfileResponse.convertToModel(): ProfileModel {
    val response = this
    return ProfileModel().apply {

    }
}