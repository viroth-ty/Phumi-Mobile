package com.pumi.app.http

import com.pumi.app.data.model.Phum
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhumiService {
    @GET("provinces")
    suspend fun getProvince(): Response<ArrayList<Phum>>

    @GET("districts")
    suspend fun getDistrict(@Query("province_id") provinceId: String): Response<ArrayList<Phum>>

    @GET("communes")
    suspend fun getCommune(@Query("district_id") districtId: String): Response<ArrayList<Phum>>

    @GET("villages")
    suspend fun getVillage(@Query("commune_id") communeId: String): Response<ArrayList<Phum>>

}