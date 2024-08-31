package com.example.domain.model

import com.example.domain.cache.CityEntity
import com.example.domain.utilis.SpinnerItem
import com.google.gson.annotations.SerializedName

data class City(
    val cityNameAr: String? = null,
    val cityNameEn: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,

    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("coord")
    var coord: Coord? = Coord(),
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("population")
    var population: Int? = null,
    @SerializedName("timezone")
    var timezone: Int? = null,
    @SerializedName("sunrise")
    var sunrise: Int? = null,
    @SerializedName("sunset")
    var sunset: Int? = null
) : SpinnerItem {
    override fun getSpinnerText(): String? {
        return cityNameEn
    }

    fun toEntity() =
        CityEntity(
            id = id,
            cityNameAr = cityNameAr,
            cityNameEn = cityNameEn,
            lat = lat,
            lon = lon,
        )
}