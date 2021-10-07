package com.example.coivd_19mvvm.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coivd_19mvvm.R
import com.example.coivd_19mvvm.custom_adapter.LocalAdapter
import com.example.coivd_19mvvm.databinding.FragmentCountriesListBinding
import com.example.coivd_19mvvm.util.Status
import com.example.coivd_19mvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesListFragment : Fragment() {

    private var _binding: FragmentCountriesListBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var myAdapter: LocalAdapter
    private val TAG = "CountriesListFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        myAdapter = LocalAdapter(requireContext())

        binding.recyclerView.apply {
            adapter = myAdapter
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }



        mainViewModel.countRes.observe(this) {

            when (it.status) {

                Status.SUCCESS -> {
                    it.data?.let { list ->
                        myAdapter.countriesList = list
                    }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    Log.d(TAG, "initRecyclerView: error message ${it.message}")
                }
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}