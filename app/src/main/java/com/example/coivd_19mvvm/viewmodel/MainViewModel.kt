package com.example.coivd_19mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coivd_19mvvm.data.WorldWideCases
import com.example.coivd_19mvvm.repository.MainRepository
import com.example.coivd_19mvvm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _globalRes = MutableLiveData<Resource<WorldWideCases>>()
    val globalRes: LiveData<Resource<WorldWideCases>> get() = _globalRes

    init {
        getGlobalData()
    }

    private fun getGlobalData() = viewModelScope.launch {

        _globalRes.postValue(Resource.loading(null))

        mainRepository.getGlobalData().let {

            if (it.isSuccessful) {

                _globalRes.postValue(Resource.success(it.body()))

            } else {
                _globalRes.postValue(Resource.error(it.errorBody().toString(), null))
            }

        }

    }

}