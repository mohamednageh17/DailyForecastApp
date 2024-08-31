package com.example.domain.usecase

import com.example.domain.cache.ForecastEntity
import com.example.domain.repo.ForecastRepository

class GetForecastFromCacheUseCase(
    private val repository: ForecastRepository,
) : BaseUseCase<Int, ForecastEntity?> {

    override suspend fun invoke(params: Int): ForecastEntity =
        repository.getForecastFromCash(params)

}