package com.pumi.app.http

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.pumi.app.PhumiApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


private val httpClient = OkHttpClient
    .Builder()
    .addNetworkInterceptor(ChuckerInterceptor.Builder(PhumiApp.appContext).build())
    .build()

private val retrofit = Retrofit
    .Builder()
    .client(httpClient)
    .baseUrl("https://pumiapp.herokuapp.com/pumi/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


private val resolveRetrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl("https://address.floo.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

internal val phumiService: PhumiService = retrofit.create()
internal val resolveService: ResolveService = resolveRetrofit.create()