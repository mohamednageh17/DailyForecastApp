package com.example.dailyforecastapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyforecastapp.base.ViewState
import com.example.domain.cache.CityEntity
import com.example.domain.cache.ForecastEntity
import com.example.domain.model.City
import com.example.domain.remote.request.ForecastRequest
import com.example.domain.remote.response.ForecastResponse
import com.example.domain.usecase.CacheAllCitiesUseCase
import com.example.domain.usecase.CacheForecastUseCase
import com.example.domain.usecase.FetchForecastFromRemoteUseCase
import com.example.domain.usecase.FetchCitiesFromRemoteUseCase
import com.example.domain.usecase.GetCitiesFromCacheUseCase
import com.example.domain.usecase.GetForecastFromCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchCitiesFromRemoteUseCase: FetchCitiesFromRemoteUseCase,
    private val getCitiesFromCacheUseCase: GetCitiesFromCacheUseCase,
    private val fetchForecastFromRemoteUseCase: FetchForecastFromRemoteUseCase,
    private val getForecastFromCacheUseCase: GetForecastFromCacheUseCase,
    private val cacheForecastUseCase: CacheForecastUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<ViewState<Any>>()
    val state: LiveData<ViewState<Any>> get() = _state

    var selectedCity: City? = null

    init {
        getCities()
    }


    private fun getCities() = viewModelScope.launch(Dispatchers.IO) {
        _state.postValue(ViewState.Loading)

        try {
            fetchCitiesFromRemoteUseCase.invoke(null).collect {
                withContext(Dispatchers.Main) {
                    _state.postValue(ViewState.Success(it))
                }
            }

        } catch (e: Exception) {
            readCitiesFromCache()
        }
    }

    fun fetchForecast(lat: Double, lon: Double, appId: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(ViewState.Loading)
            try {
                fetchForecastFromRemoteUseCase.invoke(
                    ForecastRequest(
                        lat = lat,
                        lon = lon,
                        appId = appId
                    )
                ).collect { forecast ->
                    withContext(Dispatchers.Main) {
                        _state.postValue(ViewState.Success(forecast))
                    }
                }
            } catch (_: Exception) {
                selectedCity?.let {
                    readForecastFromCache(it.id)
                }
            }
        }

    private fun readForecastFromCache(cityId: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = getForecastFromCacheUseCase.invoke(cityId)
            withContext(Dispatchers.Main) {
                val forecastResponse = ForecastResponse(
                    city = selectedCity,
                    list = response.forecast?.map { it.toForecastItem() } ?: listOf()
                )
                _state.postValue(ViewState.Success(forecastResponse))
            }

        } catch (e: Exception) {
            _state.postValue(ViewState.Error(e))
        }

    }

    fun cacheForecast(entity: ForecastEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                cacheForecastUseCase.invoke(entity)
            } catch (e: Exception) {
                _state.postValue(ViewState.Error(e))
            }
        }


    private fun readCitiesFromCache() = viewModelScope.launch(Dispatchers.IO) {
        try {

            getCitiesFromCacheUseCase.invoke(null).collect {
                withContext(Dispatchers.Main) {
                    _state.postValue(ViewState.Success(it))
                }
            }

        } catch (e: Exception) {
            _state.postValue(ViewState.Error(e))
        }

    }


}