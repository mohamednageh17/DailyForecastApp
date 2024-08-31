package com.example.domain.usecase

import com.example.domain.cache.ForecastEntity
import com.example.domain.repo.ForecastRepository

class CacheForecastUseCase(
    private val repository: ForecastRepository,
) : BaseUseCase<ForecastEntity, Unit> {

    override suspend fun invoke(params: ForecastEntity) =
        repository.insertForecast(params)
}