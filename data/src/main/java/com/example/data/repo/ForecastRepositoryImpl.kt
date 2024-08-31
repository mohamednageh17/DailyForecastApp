package com.example.data.repo

import android.util.Log
import com.example.data.cache.AppDao
import com.example.data.remote.ForecastApiService
import com.example.data.utilis.getResponse
import com.example.domain.cache.ForecastEntity
import com.example.domain.remote.request.ForecastRequest
import com.example.domain.repo.ForecastRepository
import com.example.domain.remote.response.ForecastResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ForecastRepositoryImpl(
    private val forecastApiService: ForecastApiService,
    private val appDao: AppDao
) : ForecastRepository {

    override suspend fun fetchForecastFromRemote(
        request: ForecastRequest
    ): Flow<ForecastResponse?> {
        return flow {
            emit(
                forecastApiService
                    .getForecast(request.lat, request.lon, request.appId)
                    .getResponse()
            )
        }
    }


    override suspend fun insertForecast(cityEntity: ForecastEntity) =
        appDao.insertForecast(cityEntity)


    override suspend fun getForecastFromCash(citId: Int) = appDao.getForecast(citId)


}