package com.example.domain.remote.request

data class ForecastRequest(
    var lat:Double,
    var lon:Double,
    var appId:String
)
