package com.example.baseapp.common.util

import com.chauthai.swipereveallayout.BuildConfig
import java.util.*

object PackageUtil {

    fun getPackageName(): String {
        return BuildConfig.APPLICATION_ID
    }

    fun getUUID(): String {
        return UUID.randomUUID().toString()
    }
}