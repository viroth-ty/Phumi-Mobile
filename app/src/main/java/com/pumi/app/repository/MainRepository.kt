package com.pumi.app.repository

import android.content.Context
import com.pumi.app.data.model.Phum
import com.pumi.app.data.model.Resolve
import com.pumi.app.data.model.ResolveBody
import com.pumi.app.data.result.ResultOf
import com.pumi.app.http.PhumiService
import com.pumi.app.http.ResolveService
import retrofit2.Response

class MainRepository(
    var context: Context? = null,
    var service: PhumiService? = null,
    var resolveService: ResolveService? = null,
) {
    suspend fun getProvince(): ResultOf<ArrayList<Phum>> {
        return safeApiCall {
            service!!.getProvince()
        }
    }

    suspend fun getDistrict(provinceId: String): ResultOf<ArrayList<Phum>> {
        return safeApiCall {
            service!!.getDistrict(provinceId = provinceId)
        }
    }

    suspend fun getCommune(districtId: String): ResultOf<ArrayList<Phum>> {
        return safeApiCall {
            service!!.getCommune(districtId = districtId)
        }
    }

    suspend fun getVillage(communeId: String): ResultOf<ArrayList<Phum>> {
        return safeApiCall {
            service!!.getVillage(communeId = communeId)
        }
    }

    suspend fun getCurrentLocation(resolveBody: ResolveBody): ResultOf<Resolve> {
        return safeApiCall {
            resolveService!!.currentLocation(body = resolveBody)
        }
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultOf<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                ResultOf.Success(response.body()!!)
            } else {
                ResultOf.Error(response.errorBody()?.toString() ?: "Something went wrong")
            }

        } catch (e: Exception) {
            println(e.localizedMessage)
            ResultOf.Error(e.message ?: "Internet error runs")
        }
    }
}