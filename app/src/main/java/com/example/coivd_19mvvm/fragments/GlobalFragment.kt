package com.example.coivd_19mvvm.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.coivd_19mvvm.NavGraphDirections
import com.example.coivd_19mvvm.connectivity.NetworkStatus
import com.example.coivd_19mvvm.databinding.FragmentGlobalBinding
import com.example.coivd_19mvvm.util.Status
import com.example.coivd_19mvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class GlobalFragment : Fragment() {

    private var _binding: FragmentGlobalBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private val TAG = "GlobalFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGlobalBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Internet and network checking
        val networkStatus = NetworkStatus(requireContext())

        networkStatus.observe(this) { isAvailable ->

            when (isAvailable) {

                true -> {
                    fetchGlobalData()
                }
                false -> {
                    binding.shimmerLayout.visibility = View.GONE
                    binding.mainLayout.visibility = View.GONE
                    binding.gbNoInternet.visibility = View.VISIBLE
                }

            }

        }

    }

    private fun fetchGlobalData() {

        mainViewModel.globalRes.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {

                    binding.shimmerLayout.visibility = View.GONE
                    binding.gbNoInternet.visibility = View.GONE
                    binding.mainLayout.visibility = View.VISIBLE
                    it.data?.let { it1 ->
                        binding.apply {
                            val nf = NumberFormat.getInstance(Locale.US)
                            nf.apply {

                                todayCases.text = format(it1.todayCases).toString()
                                todayDeaths.text = format(it1.todayDeaths).toString()
                                todayRecovered.text = format(it1.todayRecovered).toString()
                                active.text = format(it1.active).toString()
                                updated.text = format(it1.updated).toString()

                            }
                        }
                    }
                }
                Status.LOADING -> {
                    binding.shimmerLayout.visibility = View.VISIBLE
                    binding.mainLayout.visibility = View.GONE
                    binding.gbNoInternet.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.gbNoInternet.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}