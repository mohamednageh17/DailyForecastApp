package com.example.domain.repo

import com.example.domain.cache.CityEntity
import com.example.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun fetchCitiesFromRemote(): Flow<List<City>>

    suspend fun insertCities(list: List<CityEntity>)

    suspend fun getCitiesFromCache(): Flow<List<City>>
}