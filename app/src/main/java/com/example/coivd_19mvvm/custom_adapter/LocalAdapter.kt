package com.example.coivd_19mvvm.custom_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.BlurTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.example.coivd_19mvvm.R
import com.example.coivd_19mvvm.data.local.CountriesItem
import com.example.coivd_19mvvm.databinding.LocalDataDisplayBinding
import javax.inject.Inject

class LocalAdapter @Inject constructor(val context: Context):
    RecyclerView.Adapter<LocalAdapter.LocalViewHolder>() {

    class LocalViewHolder(val itemBinding: LocalDataDisplayBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    private val diffCallBack = object : DiffUtil.ItemCallback<CountriesItem>() {
        override fun areItemsTheSame(oldItem: CountriesItem, newItem: CountriesItem): Boolean {
            return newItem.countryInfo._id == oldItem.countryInfo._id
        }

        override fun areContentsTheSame(oldItem: CountriesItem, newItem: CountriesItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differList = AsyncListDiffer(this, diffCallBack)

    var countriesList: List<CountriesItem>
        get() = differList.currentList
        set(value) {
            differList.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        return LocalViewHolder(
            LocalDataDisplayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        val currentCountry = countriesList[position]

        holder.itemBinding.apply {

            tvCountriesName.text = currentCountry.country
            flagImageView.load(currentCountry.countryInfo.flag){
                crossfade(true)
                crossfade(500)
                transformations(BlurTransformation(context))
                transformations(GrayscaleTransformation())
                transformations(RoundedCornersTransformation(15f))
                placeholder(R.drawable.ic_baseline_image_24)
            }

        }
    }

    override fun getItemCount() = countriesList.size


}