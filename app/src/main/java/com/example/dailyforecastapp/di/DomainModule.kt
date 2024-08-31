package com.example.dailyforecastapp.di

import com.example.domain.repo.CityRepository
import com.example.domain.repo.ForecastRepository
import com.example.domain.usecase.CacheAllCitiesUseCase
import com.example.domain.usecase.CacheForecastUseCase
import com.example.domain.usecase.FetchForecastFromRemoteUseCase
import com.example.domain.usecase.FetchCitiesFromRemoteUseCase
import com.example.domain.usecase.GetCitiesFromCacheUseCase
import com.example.domain.usecase.GetForecastFromCacheUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun fetchCitiesFromRemoteUseCase(cityRepository: CityRepository) = FetchCitiesFromRemoteUseCase(cityRepository)

    @Provides
    @Singleton
    fun provideFetchForecastFromRemoteUseCase(repository: ForecastRepository) =
        FetchForecastFromRemoteUseCase(repository)


    @Provides
    @Singleton
    fun cacheForecastUseCase(repository: ForecastRepository) = CacheForecastUseCase(repository)

    @Provides
    @Singleton
    fun cacheAllCitiesUseCase(repository: CityRepository) =
        CacheAllCitiesUseCase(repository)

    @Provides
    @Singleton
    fun getForecastFromCacheUseCase(repository: ForecastRepository) =
        GetForecastFromCacheUseCase(repository)

    @Provides
    @Singleton
    fun getCitiesFromCacheUseCase(repository: CityRepository) =
        GetCitiesFromCacheUseCase(repository)

}