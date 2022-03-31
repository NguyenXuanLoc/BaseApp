package com.example.baseapp.common.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.baseapp.ui.TestActivity

class ScreenReceiver : BroadcastReceiver() {
    var TAG = "ScreenReceiver"
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(TAG, "onReceive: ")
        context?.startActivity(
            Intent(
                context,
                TestActivity::class.java
            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}