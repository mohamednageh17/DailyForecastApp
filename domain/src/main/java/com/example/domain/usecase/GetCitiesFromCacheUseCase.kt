package com.example.domain.usecase

import com.example.domain.model.City
import com.example.domain.repo.CityRepository
import kotlinx.coroutines.flow.Flow

class GetCitiesFromCacheUseCase (
    private val cityRepository: CityRepository
) : BaseUseCase<Void?, Flow<List<City>>> {

    override suspend fun invoke(params: Void?): Flow<List<City>> =
        cityRepository.getCitiesFromCache()
}