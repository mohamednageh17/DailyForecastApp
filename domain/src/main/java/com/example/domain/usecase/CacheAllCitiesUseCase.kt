package com.example.domain.usecase

import com.example.domain.cache.CityEntity
import com.example.domain.repo.CityRepository

class CacheAllCitiesUseCase(
    private val repository: CityRepository,
) : BaseUseCase<List<CityEntity>, Unit> {

    override suspend fun invoke(params: List<CityEntity>) =
        repository.cacheCities(params)
}