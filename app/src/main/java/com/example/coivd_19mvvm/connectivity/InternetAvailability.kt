package com.example.coivd_19mvvm.connectivity

import android.util.Log
import androidx.lifecycle.LiveData
import java.net.InetSocketAddress
import java.net.Socket

class InternetAvailability {

    private val TAG = "InternetAvailability"

    fun check(): Boolean {

        return try {
            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8", 53))
            socket.close()
            Log.i(TAG, "check: access")
            true
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i(TAG, "check: no access")
            false
        }
    }
}