package com.example.coivd_19mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
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

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            setSupportActionBar(toolbar)

            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

            navController = navHostFragment.navController

            appBarConfiguration = AppBarConfiguration(
                setOf(R.id.globalFragment, R.id.countriesListFragment)
            )

            setupActionBarWithNavController(navController, appBarConfiguration)

            bottomNav.setupWithNavController(navController)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}