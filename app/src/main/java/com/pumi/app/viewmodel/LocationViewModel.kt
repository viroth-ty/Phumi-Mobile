package com.pumi.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pumi.app.PhumiApp
import com.pumi.app.data.model.Resolve
import com.pumi.app.data.model.ResolveBody
import com.pumi.app.data.result.ResultOf
import com.pumi.app.listener.LocationListener
import com.pumi.app.repository.MainRepository
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel() {

    val location: MutableLiveData<LocationListener> = MutableLiveData()
    val resolve: MutableLiveData<Resolve> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun setLocation(locationListener: LocationListener) {
        viewModelScope.launch {
            location.value = locationListener
        }
    }

    fun getCurrentLocation(resolveBody: ResolveBody) {
        viewModelScope.launch {
            loading.postValue(true)
            when(val result = PhumiApp.mainRepository.getCurrentLocation(resolveBody = resolveBody)) {
                is ResultOf.Success -> {
                    loading.postValue(false)
                    resolve.postValue(result.data)
                }

                is ResultOf.Error -> {
                    loading.postValue(false)
                    errorMessage.postValue(result.exception)
                }

            }
        }
    }
}