package com.example.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.cache.CityEntity
import com.example.domain.cache.ForecastEntity

@Database(entities = [CityEntity::class, ForecastEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun forecastDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: ForecastDatabase? = null

        fun getDatabase(context: Context): ForecastDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ForecastDatabase::class.java,
                    "forecast_database"
                )
                    .addTypeConverter(Converters())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}