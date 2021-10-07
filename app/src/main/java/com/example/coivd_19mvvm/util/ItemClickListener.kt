package com.example.coivd_19mvvm.util

import com.example.coivd_19mvvm.data.local.CountriesItem

interface ItemClickListener {
    fun onClick(currentItem: CountriesItem)
}