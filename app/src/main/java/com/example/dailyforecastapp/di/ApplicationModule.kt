package com.example.dailyforecastapp.di

import android.app.Application
import android.content.Context
import com.example.dailyforecastapp.BuildConfig
import com.example.dailyforecastapp.di.qualifiers.AppBuildType
import com.example.dailyforecastapp.di.qualifiers.AppContext
import com.example.dailyforecastapp.di.qualifiers.CitiesBaseURL
import com.example.dailyforecastapp.di.qualifiers.ForecastBaseURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @AppContext
    @Provides
    fun context(application: Application): Context {
        return application.applicationContext
    }

    @ForecastBaseURL
    @Provides
    fun provideBaseURl(): String {
        return BuildConfig.FORECAST_BASE_URL
    }
    @CitiesBaseURL
    @Provides
    fun provideCitiesBaseURl(): String {
        return BuildConfig.CITIES_BASE_URL
    }

    @AppBuildType
    @Provides
    fun provideBuildType(): Boolean {
        return BuildConfig.DEBUG
    }

}