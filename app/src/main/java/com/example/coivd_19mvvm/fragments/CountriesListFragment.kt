package com.example.coivd_19mvvm.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coivd_19mvvm.connectivity.NetworkStatus
import com.example.coivd_19mvvm.custom_adapter.LocalAdapter
import com.example.coivd_19mvvm.data.local.CountriesItem
import com.example.coivd_19mvvm.databinding.FragmentCountriesListBinding
import com.example.coivd_19mvvm.util.ItemClickListener
import com.example.coivd_19mvvm.util.Status
import com.example.coivd_19mvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesListFragment : Fragment(), ItemClickListener {

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

        searchBar()

        initRecyclerView()
    }

    // EDITED on 14/01/2022
    private fun searchBar() {
        binding.searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                myAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun initRecyclerView() {
        myAdapter = LocalAdapter(requireContext(), this)

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

        NetworkStatus(requireContext()).observe(this) {

            when (it) {
                true -> {
                    fetchList()
                }
                false -> {
                    binding.apply {
                        listNoInternet.visibility = View.VISIBLE
                        listMainView.visibility = View.GONE
                        shimmerLayoutList.visibility = View.GONE
                    }
                }
            }

        }
    }

    private fun fetchList() {
        mainViewModel.countRes.observe(this) {
            when (it.status) {

                Status.SUCCESS -> {
                    it.data?.let { list ->
                        binding.apply {
                            shimmerLayoutList.visibility = View.GONE
                            listMainView.visibility = View.VISIBLE
                            listNoInternet.visibility = View.GONE
                        }
                        myAdapter.countriesList = list
                    }
                }
                Status.LOADING -> {
                    binding.apply {
                        shimmerLayoutList.visibility = View.VISIBLE
                        listMainView.visibility = View.GONE
                        listNoInternet.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    binding.listNoInternet.visibility = View.VISIBLE
                    Log.d(TAG, "initRecyclerView: error message ${it.message}")
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(currentItem: CountriesItem) {
        val directions = CountriesListFragmentDirections
            .actionCountriesListFragmentToCountrySpecificFragment(currentItem)
        findNavController().navigate(directions)
    }

}