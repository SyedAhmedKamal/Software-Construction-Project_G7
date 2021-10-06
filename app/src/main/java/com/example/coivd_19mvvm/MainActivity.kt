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
                        fetchData()
                        fetchCountries()
                        Log.d(TAG, "checkConnection: Connected")
                    }
                    false -> {
                        Log.d(TAG, "checkConnection: NotConnected")
                    }

                }

            }

        }

    }

    private fun fetchCountries() {

        mainViewModel.countRes.observe(this) {

            when (it.status) {

                Status.SUCCESS -> {
                    it.data?.let { list ->

                        val iterator = list.iterator()
                        Log.i(TAG, "fetchCountries: size ${list.size}")

                        if (iterator.hasNext()) {
                            iterator.forEach { countriesItem ->
                                Log.i(
                                    TAG,
                                    "fetchCountries: Countries names: ${countriesItem.country}, " +
                                            "Info ${countriesItem.countryInfo.iso2}"
                                )
                            }
                        }
                    }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }

        }

    }

    private fun fetchData() {

        mainViewModel.globalRes.observe(this) {
            when (it.status) {

                Status.SUCCESS -> {
                    Log.i(TAG, "fetchData: ProgressBar gone")
                    it.data?.let { it1 ->
                        it1.apply {
                            Log.i(TAG, "fetchData: ${it1.cases} \t ${it1.population}")
                        }
                    }
                }
                Status.LOADING -> {
                    Log.i(TAG, "fetchData: ProgressBar visible")
                    Log.i(TAG, "fetchData: data gone")
                }
                Status.ERROR -> {
                    Log.i(TAG, "fetchData: ProgressBar gone")
                    Log.i(TAG, "fetchData: Error occur")
                }
            }
        }

    }
}