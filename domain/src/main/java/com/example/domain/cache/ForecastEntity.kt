package com.example.domain.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.ForecastItem
import com.example.domain.model.Main
import com.example.domain.model.Weather


@Entity(tableName = "forecast_table")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long=0,
    @ColumnInfo(name = "city_id")
    var cityId: Int,
    @ColumnInfo(name = "forecast")
    var forecast: List<ForecastEntityList>? = null
)

data class ForecastEntityList(
    var temp: Double? = null,
    var feelsLike: Double? = null,
    var tempMin: Double? = null,
    var tempMax: Double? = null,
    var pressure: Int? = null,
    var seaLevel: Int? = null,
    var grndLevel: Int? = null,
    var humidity: Int? = null,
    var tempKf: Double? = null,
    var dtTxt: String? = null,
    var weatherDesc: String? = null

) {
    fun toForecastItem() = ForecastItem(
        dtTxt = dtTxt,
        main = Main(
            temp = temp,
            feelsLike = feelsLike,
            tempMin = tempMin,
            tempMax = tempMax,
            pressure = pressure,
            seaLevel = seaLevel,
            grndLevel = grndLevel,
            humidity = humidity,
            tempKf = tempKf
        ),
        weather = arrayListOf(
            Weather(description = weatherDesc)
        )
    )
}
