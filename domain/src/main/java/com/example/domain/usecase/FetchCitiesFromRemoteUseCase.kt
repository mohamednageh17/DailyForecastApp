package com.example.domain.usecase

import com.example.domain.model.City
import com.example.domain.repo.CityRepository
import com.example.domain.repo.ForecastRepository
import kotlinx.coroutines.flow.Flow


class FetchCitiesFromRemoteUseCase (
    private val repository: CityRepository
) : BaseUseCase<Void?, Flow<List<City>>> {

    override suspend fun invoke(params: Void?): Flow<List<City>> =
        repository.fetchCitiesFromRemote()
}