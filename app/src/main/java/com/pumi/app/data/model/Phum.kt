package com.pumi.app.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Phum(
    val id: String,

    @SerializedName("administrative_unit")
    val administrativeUnit: AdministrativeUnit,

    @SerializedName("name_km")
    val nameKM: String,

    @SerializedName("name_latin")
    val nameLatin: String,

    @SerializedName("name_en")
    val nameEn: String,

    @SerializedName("full_name_km")
    val fullNameKM: String,

    @SerializedName("full_name_latin")
    val fullNameLatin: String,

    @SerializedName("full_name_en")
    val fullNameEn: String,

    @SerializedName("province_id")
    val provinceId: String? = null,

    @SerializedName("address_km")
    val addressKM: String,

    @SerializedName("address_latin")
    val addressLatin: String,

    @SerializedName("address_en")
    val addressEn: String,
)

@Serializable
data class AdministrativeUnit(
    @SerializedName("name_km")
    val nameKM: String,

    @SerializedName("name_latin")
    val nameLatin: String,

    @SerializedName("name_en")
    val nameEn: String,
)
