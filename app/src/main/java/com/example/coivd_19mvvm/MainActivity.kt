package com.example.coivd_19mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coivd_19mvvm.connectivity.InternetAvailability
import com.example.coivd_19mvvm.connectivity.NetworkStatus
import com.example.coivd_19mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Internet and network checking

        val networkStatus = NetworkStatus(application)
        val internetAvailability = InternetAvailability()

        binding.apply {

            networkStatus.observe(this@MainActivity) { isAvailable ->

                when (isAvailable) {

                    true -> {
                        tv.text = "Connected"
                        Log.d(TAG, "checkConnection: Connected")
                    }
                    false -> {
                        tv.text = "NotConnected"
                        Log.d(TAG, "checkConnection: NotConnected")
                    }

                }

            }

        }

    }
}