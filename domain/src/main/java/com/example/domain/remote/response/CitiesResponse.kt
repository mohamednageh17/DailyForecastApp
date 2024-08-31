package com.example.domain.remote.response

import com.example.domain.model.City
import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName("cities") var cities: ArrayList<City> = arrayListOf()
)
