package com.pumi.app.http

import com.pumi.app.data.model.Resolve
import com.pumi.app.data.model.ResolveBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ResolveService {

    @Headers("Content-Type: application/json")
    @POST("resolve")
    suspend fun currentLocation(@Body body: ResolveBody): Response<Resolve>

}