package com.example.baseapp.common.ext

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.baseapp.R
import com.example.baseapp.common.MyApplication
import org.jetbrains.anko.toast
import org.json.JSONObject
import timber.log.Timber

fun Context.networkIsConnected(): Boolean {
    try {
        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return conMgr?.activeNetworkInfo?.isConnected ?: false
    } catch (e: Exception) {
        Timber.e("$e")
    }

    return false
}


fun Context.showNetworkError() {
    toast("R.string.err_network_not_available")
}

fun Context.showApiCallError(code: Int) {
    toast(String.format(getString(R.string.err_common), code))
}

fun Context.requestDrawOverlayPermission(requestCode: Int) {
    val intent = Intent(
        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        Uri.parse("package:$packageName")
    )
    when (this) {
        is AppCompatActivity -> {
            startActivityForResult(intent, requestCode)
        }
        is Fragment -> {
            startActivityForResult(intent, requestCode)
        }
    }
}

fun Context.requestAccessibilitiPermission(requestCode: Int) {
    val intent = Intent(
        Settings.ACTION_ACCESSIBILITY_SETTINGS
    )
    when (this) {
        is AppCompatActivity -> {
            startActivityForResult(intent, requestCode)
        }
        is Fragment -> {
            startActivityForResult(intent, requestCode)
        }
    }
}

fun Context.openAppSettingsPage() {
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        data = Uri.parse("package:$packageName")
    }.run {
        startActivity(this)
    }
}

fun Context.convertMapToJSONObject(map: Map<String, Any?>): JSONObject {
    val jsonObject = JSONObject()
    for (key in map.keys) {
        val value = if (map[key] is Map<*, *>) {
            convertMapToJSONObject(map[key] as Map<String, Any?>).toString()
        } else {
            map[key]
        }
        jsonObject.put(key, value)
    }
    return jsonObject
}

val Context.sharedPref: SharedPreferences
    get() = MyApplication.instance.sharedPref