package com.example.dailyforecastapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.dailyforecastapp.BuildConfig
import com.example.dailyforecastapp.R
import com.example.dailyforecastapp.base.ViewState
import com.example.dailyforecastapp.databinding.ActivityMainBinding
import com.example.dailyforecastapp.utilis.setAsSpinner
import com.example.domain.cache.CityEntity
import com.example.domain.cache.ForecastEntity
import com.example.domain.model.City
import com.example.domain.remote.response.ForecastResponse
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    private val adapter by lazy { ForecastAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initForecastRV()
        observe()
    }

    private fun initCities(data: List<City>) {
        binding.inputCity.setAsSpinner(
            binding.citiesSpinner,
            data,
        ) { city ->
            adapter.clear()
            getForecast(city)
        }
    }

    private fun initForecastRV() {
        binding.forecastRV.adapter = adapter
    }

    private fun getForecast(city: City?) {
        city?.let {
            viewModel.selectedCity = it
            viewModel.fetchForecast(it.lat ?: 0.0, it.lon ?: 0.0, BuildConfig.FORECAST_APP_ID)
        }
    }

    private fun cacheAllCities(list: List<CityEntity>) {
        viewModel.cacheAllCities(list)
    }

    private fun cacheForecast(forecast: ForecastResponse) {
        viewModel.selectedCity?.let {
            val forecastEntity =
                ForecastEntity(cityId = it.id, forecast = forecast.list.map { it.totEntity() })

            try {
                viewModel.cacheForecast(forecastEntity)
            } catch (_: Exception) {
            }
        }
    }

    private fun observe() {

        viewModel.state.observe(this) { state ->

            binding.errorArea.isVisible = state is ViewState.Error
            binding.prgressBar.isVisible = state is ViewState.Loading

            when (state) {
                is ViewState.Loading -> {
                }

                is ViewState.Error -> {
                    Log.e(TAG, "error:${state.exeption.message} ")
                }

                is ViewState.Success -> {
                    when (state.data) {
                        is List<*> -> {
                            (state.data as List<City>).apply {
                                initCities(this)

                                cacheAllCities(this.map { it.toEntity() })
                            }
                        }

                        is ForecastResponse -> {
                            state.data.apply {
                                adapter.setData(this.list)
                                cacheForecast(this)
                            }

                        }
                    }
                }
            }
        }
    }
}