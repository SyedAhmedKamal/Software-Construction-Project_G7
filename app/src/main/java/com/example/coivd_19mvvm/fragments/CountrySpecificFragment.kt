package com.example.coivd_19mvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coivd_19mvvm.R
import com.example.coivd_19mvvm.databinding.FragmentCountrySpecificBinding

class CountrySpecificFragment : Fragment() {

    private var _binding: FragmentCountrySpecificBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountrySpecificBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}