package com.example.baseapp.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class MyAccessbilityService : AccessibilityService() {
    var TAG = "MyAccessbilityService";
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.e(TAG, "onAccessibilityEvent: ")
    }

    override fun onInterrupt() {
        Log.e(TAG, "onInterrupt: ")
    }

    override fun onServiceConnected() {
        var info = AccessibilityServiceInfo()
        info.apply {
//            eventTypes =
//                AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED or AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START or AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END or AccessibilityEvent.TYPE_TOUCH_INTERACTION_END or AccessibilityEvent.TYPE_TOUCH_INTERACTION_START
            eventTypes =
                AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_SCROLLED
//            or AccessibilityEvent.TYPE_VIEW_SELECTED
//            or AccessibilityEvent.TYPE_VIEW_FOCUSED or AccessibilityEvent.TYPE_TOUCH_INTERACTION_START
            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN

            notificationTimeout = 100
        }
        Log.e(TAG, "onServiceConnected: ")
        this.serviceInfo = info
        super.onServiceConnected()
    }
}