package com.example.dailyforecastapp.di

import android.content.Context
import com.example.dailyforecastapp.di.qualifiers.AppContext
import com.example.data.cache.AppDao
import com.example.data.cache.ForecastDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideCartDataBase(
        @AppContext context: Context,
    ): ForecastDatabase {
        return ForecastDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideEntriesDao(
        database: ForecastDatabase,
    ): AppDao = database.forecastDao()
}