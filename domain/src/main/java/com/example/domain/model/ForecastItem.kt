package com.example.domain.model

import com.example.domain.cache.ForecastEntityList
import com.google.gson.annotations.SerializedName


data class ForecastItem(

    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("main") var main: Main? = Main(),
    @SerializedName("weather") var weather: ArrayList<Weather> = arrayListOf(),
    @SerializedName("clouds") var clouds: Clouds? = Clouds(),
    @SerializedName("wind") var wind: Wind? = Wind(),
    @SerializedName("visibility") var visibility: Int? = null,
    @SerializedName("pop") var pop: Float? = null,
    @SerializedName("sys") var sys: Sys? = Sys(),
    @SerializedName("dt_txt") var dtTxt: String? = null

){
    fun totEntity() = ForecastEntityList(
        temp = main?.temp,
        feelsLike = main?.feelsLike,
        tempMin = main?.tempMin,
        tempMax = main?.tempMax,
        pressure = main?.pressure,
        seaLevel = main?.seaLevel,
        grndLevel = main?.grndLevel,
        humidity = main?.humidity,
        tempKf = main?.tempKf,
        dtTxt = dtTxt,
        weatherDesc = weather.firstOrNull()?.description
    )
}