package com.example.data.remote

import com.example.domain.remote.response.CitiesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CityApiService {
    @GET("uploads/cities.json")
    suspend fun getCities(): Response<CitiesResponse>
}