package com.leguer.app.core

import android.util.Log
import com.leguer.app.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) {
            Log.e(TAG, e.message ?: e.toString())
        }
    }
}