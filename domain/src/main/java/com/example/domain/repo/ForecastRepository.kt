package com.example.domain.repo

import com.example.domain.cache.ForecastEntity
import com.example.domain.remote.request.ForecastRequest
import com.example.domain.remote.response.ForecastResponse
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    suspend fun fetchForecastFromRemote(request: ForecastRequest): Flow<ForecastResponse?>

    suspend fun insertForecast(cityEntity: ForecastEntity)

    suspend fun getForecastFromCash(citId: Int): ForecastEntity

}