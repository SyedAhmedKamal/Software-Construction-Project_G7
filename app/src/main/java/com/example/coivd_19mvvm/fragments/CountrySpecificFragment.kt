package com.example.coivd_19mvvm.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.coivd_19mvvm.R
import com.example.coivd_19mvvm.data.local.CountriesItem
import com.example.coivd_19mvvm.databinding.ActivityMainBinding
import com.example.coivd_19mvvm.databinding.FragmentCountrySpecificBinding
import java.text.NumberFormat
import java.util.*

class CountrySpecificFragment : Fragment() {

    private var _binding: FragmentCountrySpecificBinding? = null
    private lateinit var mainBinding: ActivityMainBinding
    private val binding get() = _binding!!
    private lateinit var countriesItem: CountriesItem

    private val args: CountrySpecificFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            args.countryArgs.apply {
                NumberFormat.getInstance(Locale.US).apply {

                    spTodayCases.text = format(todayCases).toString()
                    spTodayDeaths.text = format(todayDeaths).toString()
                    spTodayRecovered.text = format(todayRecovered).toString()

                }

                spCtName.text = country
                spFlag.load(countryInfo.flag){
                    crossfade(true)
                    transformations(RoundedCornersTransformation(12f))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}