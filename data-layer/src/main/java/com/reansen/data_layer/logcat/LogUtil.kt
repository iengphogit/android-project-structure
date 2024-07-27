package com.reansen.data_layer.logcat

import android.util.Log


object LogUtil {
    // Check if debug mode is enabled
    private var isDebug = true // Change this based on your build type or configuration

    // Set debug mode at runtime
    fun setDebugMode(debugMode: Boolean) {
        isDebug = debugMode
    }

    fun d(tag: String?, message: String?) {
        if (isDebug) {
            Log.d(tag, message!!)
        }
    }

    fun e(tag: String?, message: String?) {
        if (isDebug) {
            Log.e(tag, message!!)
        }
    }

    fun i(tag: String?, message: String?) {
        if (isDebug) {
            Log.i(tag, message!!)
        }
    }

    fun w(tag: String?, message: String?) {
        if (isDebug) {
            Log.w(tag, message!!)
        }
    }

    fun v(tag: String?, message: String?) {
        if (isDebug) {
            Log.v(tag, message!!)
        }
    }
}
