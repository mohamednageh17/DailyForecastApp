package com.example.domain.remote.response

import com.example.domain.model.City
import com.example.domain.model.ForecastItem
import com.google.gson.annotations.SerializedName


data class ForecastResponse(
    @SerializedName("cod") var cod: String? = null,
    @SerializedName("message") var message: Int? = null,
    @SerializedName("cnt") var cnt: Int? = null,
    @SerializedName("list") var list: List<ForecastItem> = arrayListOf(),
    @SerializedName("city") var city: City? = City()
)