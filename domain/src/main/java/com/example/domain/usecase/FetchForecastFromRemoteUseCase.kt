package com.example.domain.usecase

import com.example.domain.remote.request.ForecastRequest
import com.example.domain.remote.response.ForecastResponse
import com.example.domain.repo.ForecastRepository
import kotlinx.coroutines.flow.Flow

class FetchForecastFromRemoteUseCase(private val forecastRepository: ForecastRepository) :
    BaseUseCase<ForecastRequest, Flow<ForecastResponse?>> {
    override suspend fun invoke(params: ForecastRequest): Flow<ForecastResponse?> =
        forecastRepository.fetchForecastFromRemote(params)
}