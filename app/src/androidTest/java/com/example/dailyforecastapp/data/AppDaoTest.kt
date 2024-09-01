package com.example.dailyforecastapp.data

import com.example.data.cache.AppDao
import com.example.domain.cache.CityEntity
import com.example.domain.cache.ForecastEntity
import com.example.domain.cache.ForecastEntityList
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AppDaoTest {

    private val fakeCityEntities = listOf(
        CityEntity(1, "City 1"),
        CityEntity(2, "City 2")
    )

    private val fakeForecastEntity = ForecastEntity(
        cityId = 1, forecast = listOf(
            ForecastEntityList(),
            ForecastEntityList()
        )
    )

    private val fakeCityId = 1

    private val appDao = FakeAppDao()

    @Test
    fun `insertCities should insert cities correctly`() = runBlocking {
        appDao.insertCities(fakeCityEntities)

        val citiesFlow = appDao.getCities()
        val cities = mutableListOf<CityEntity>()
        citiesFlow.collect { cities.addAll(it) }

        assertThat(cities).containsExactlyElementsIn(fakeCityEntities).inOrder()
    }

    @Test
    fun `insertForecast should insert forecast correctly`() = runBlocking {
        appDao.insertForecast(fakeForecastEntity)

        val retrievedForecast = appDao.getForecast(fakeCityId)

        assertThat(retrievedForecast).isEqualTo(fakeForecastEntity)
    }

    // Fake implementation of the AppDao for testing
    class FakeAppDao : AppDao {
        private val cityTable = mutableListOf<CityEntity>()
        private var forecastEntity: ForecastEntity? = null

        override fun insertCities(items: List<CityEntity>) {
            cityTable.addAll(items)
        }

        override fun getCities(): Flow<List<CityEntity>> {
            return flow { emit(cityTable) }
        }

        override fun insertForecast(item: ForecastEntity) {
            forecastEntity = item
        }

        override fun getForecast(cityId: Int): ForecastEntity {
            return forecastEntity
                ?: throw IllegalStateException("No forecast found for cityId: $cityId")
        }
    }
}