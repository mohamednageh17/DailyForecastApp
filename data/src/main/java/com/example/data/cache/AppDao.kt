package com.example.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.cache.CityEntity
import com.example.domain.cache.ForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(items: List<CityEntity>)

    @Query("SELECT * FROM city_table")
    fun getCities(): Flow<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(item: ForecastEntity)

    @Query("SELECT * FROM forecast_table where city_id=:cityId")
    fun getForecast(cityId: Int): ForecastEntity


}