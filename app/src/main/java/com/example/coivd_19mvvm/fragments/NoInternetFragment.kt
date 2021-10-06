package com.example.coivd_19mvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.coivd_19mvvm.NavGraphDirections
import com.example.coivd_19mvvm.R
import com.example.coivd_19mvvm.connectivity.NetworkStatus
import com.example.coivd_19mvvm.databinding.FragmentNoInternetBinding

class NoInternetFragment : Fragment(R.layout.fragment_no_internet) {

    private var _binding: FragmentNoInternetBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Internet and network checking
        val networkStatus = NetworkStatus(requireContext())

        networkStatus.observe(this) { isAvailable ->

            if (isAvailable) {
                // It will simply popUp from backstack and user navigate to previous loaded screen.
                findNavController().popBackStack()
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}