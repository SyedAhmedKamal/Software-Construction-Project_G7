package com.example.coivd_19mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.coivd_19mvvm.connectivity.InternetAvailability
import com.example.coivd_19mvvm.connectivity.NetworkStatus
import com.example.coivd_19mvvm.databinding.ActivityMainBinding
import com.example.coivd_19mvvm.util.Status
import com.example.coivd_19mvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Internet and network checking

        val networkStatus = NetworkStatus(application)


        binding.apply {

            networkStatus.observe(this@MainActivity) { isAvailable ->

                when (isAvailable) {

                    true -> {
                        tv.text = "Connected"
                        fetchData()
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

    private fun fetchData() {

        mainViewModel.globalRes.observe(this){
            when(it.status){

                Status.SUCCESS ->{
                    Log.i(TAG, "fetchData: ProgressBar gone")
                    it.data?.let {it1 ->
                        it1.apply {
                            Log.i(TAG, "fetchData: ${it1.cases} \t ${it1.population}")
                        }
                    }
                }
                Status.LOADING ->{
                    Log.i(TAG, "fetchData: ProgressBar visible")
                    Log.i(TAG, "fetchData: data gone")
                }
                Status.ERROR ->{
                    Log.i(TAG, "fetchData: ProgressBar gone")
                    Log.i(TAG, "fetchData: Error occur")
                }
            }
        }

    }
}