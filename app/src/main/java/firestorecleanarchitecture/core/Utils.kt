package firestorecleanarchitecture.core

import android.util.Log
import firestorecleanarchitecture.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception?) {
            Log.e(TAG, e?.message ?: e.toString())
        }
    }
}