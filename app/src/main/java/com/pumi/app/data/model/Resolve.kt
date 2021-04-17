package com.pumi.app.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Resolve (
    val province: Commune,
    val district: Commune,
    val commune: Commune
)

@Serializable
data class Commune (
    val code: String,
    val km: String,
    val en: String
)

@Serializable
data class ResolveBody(
    @SerializedName("lat")
    var lat: String? = null,
    @SerializedName("lng")
    var lng: String? = null
)
