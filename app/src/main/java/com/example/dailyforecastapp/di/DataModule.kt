package com.example.dailyforecastapp.di

import com.example.dailyforecastapp.di.qualifiers.CitiesBaseURL
import com.example.dailyforecastapp.di.qualifiers.ForecastBaseURL
import com.example.dailyforecastapp.di.qualifiers.ForecastRetrofit
import com.example.data.cache.AppDao
import com.example.data.remote.CityApiService
import com.example.data.remote.ForecastApiService
import com.example.data.repo.CityRepositoryImpl
import com.example.data.repo.ForecastRepositoryImpl
import com.example.domain.repo.CityRepository
import com.example.domain.repo.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {
    @Binds
    @Singleton
    abstract fun bindCityRepository(repositoryImpl: CityRepositoryImpl): CityRepository

    @Binds
    @Singleton
    abstract fun bindForecastRepository(repositoryImpl: ForecastRepositoryImpl): ForecastRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCityRepositoryImpl(cityApiService: CityApiService,  appDao: AppDao) =
        CityRepositoryImpl(cityApiService, appDao)

    @Provides
    @Singleton
    fun provideForecastRepositoryImpl(apiService: ForecastApiService, appDao: AppDao) =
        ForecastRepositoryImpl(apiService, appDao)


    @ForecastRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        @ForecastBaseURL baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideCitiesApiService(
        @CitiesBaseURL baseUrl: String,
        okHttpClient: OkHttpClient,
    ): CityApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CityApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideForecastApiService(
        @ForecastBaseURL baseUrl: String,
        okHttpClient: OkHttpClient,
    ): ForecastApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ForecastApiService::class.java)
    }

}