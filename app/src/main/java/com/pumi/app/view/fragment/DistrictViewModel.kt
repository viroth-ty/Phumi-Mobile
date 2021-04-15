package com.pumi.app.view.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pumi.app.PhumiApp
import com.pumi.app.data.model.Phum
import com.pumi.app.data.result.ResultOf
import kotlinx.coroutines.launch

class DistrictViewModel : ViewModel() {

    var phum: MutableLiveData<ArrayList<Phum>> = MutableLiveData(arrayListOf())
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getDistrict(provinceId: String) {
        viewModelScope.launch {
            loading.postValue(true)
            when (val response = PhumiApp.mainRepository.getDistrict(provinceId)) {
                is ResultOf.Success -> {
                    loading.postValue(false)
                    phum.postValue(response.data)
                }

                is ResultOf.Error -> {
                    loading.postValue(false)
                    println("error ${response.exception}")
                }
            }
        }
    }

}