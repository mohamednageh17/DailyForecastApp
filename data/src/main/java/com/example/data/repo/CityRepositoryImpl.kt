package com.example.data.repo

import android.util.Log
import com.example.data.cache.AppDao
import com.example.data.remote.CityApiService
import com.example.data.utilis.getResponse
import com.example.domain.cache.CityEntity
import com.example.domain.model.City
import com.example.domain.repo.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CityRepositoryImpl(
    private val cityApiService: CityApiService, private val appDao: AppDao
) : CityRepository {

    override suspend fun fetchCitiesFromRemote(): Flow<List<City>> = flow {
        val response = cityApiService.getCities().getResponse().cities
        cacheCities(response.map { it.toEntity() })
        emit(response)
    }

    override suspend fun cacheCities(list: List<CityEntity>) {
        try {
            appDao.insertCities(list)
            Log.d("nageh", "insertCities successfully ")
        } catch (_: Exception) {
        }
    }


    override suspend fun getCitiesFromCache(): Flow<List<City>> = flow {
        appDao.getCities().collect { cityEntities ->
            emit(cityEntities.map { it.toCity() })
        }
    }
}