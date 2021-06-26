package com.pumi.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pumi.app.PhumiApp
import com.pumi.app.data.model.Phum
import com.pumi.app.data.result.ResultOf
import kotlinx.coroutines.launch

class ProvinceViewModel : ViewModel() {

    var phum: MutableLiveData<ArrayList<Phum>> = MutableLiveData(arrayListOf())
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getProvince() {
        viewModelScope.launch {
            loading.postValue(true)
            when(val response = PhumiApp.mainRepository.getProvince()) {
                is ResultOf.Success -> {
                    loading.postValue(false)
                    phum.postValue(response.data)
                }
                is ResultOf.Error -> {
                    print("error ${response.exception}")
                    loading.postValue(false)
                }
            }
        }
    }

}