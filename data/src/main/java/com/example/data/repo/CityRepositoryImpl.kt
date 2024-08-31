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
        emit(cityApiService.getCities().getResponse().cities)
    }

    override suspend fun insertCities(list: List<CityEntity>) = appDao.insertCities(list)


    override suspend fun getCitiesFromCache(): Flow<List<City>> = flow {
        appDao.getCities().collect { cityEntities ->
            emit(cityEntities.map { it.toCity() })
        }
    }
}