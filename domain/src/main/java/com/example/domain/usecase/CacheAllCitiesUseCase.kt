package com.example.domain.usecase

import com.example.domain.cache.CityEntity
import com.example.domain.repo.CityRepository
import com.example.domain.repo.ForecastRepository
import kotlinx.coroutines.flow.Flow

class CacheAllCitiesUseCase(
    private val repository: CityRepository,
) : BaseUseCase<List<CityEntity>, Unit> {

    override suspend fun invoke(params: List<CityEntity>) =
        repository.insertCities(params)
}