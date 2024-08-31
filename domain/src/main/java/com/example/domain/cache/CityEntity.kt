package com.example.domain.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.City

@Entity(tableName = "city_table")
data class CityEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int=0,
    @ColumnInfo(name = "cityNameAr")
    val cityNameAr: String? = null,
    @ColumnInfo(name = "cityNameEn")
    val cityNameEn: String? = null,
    @ColumnInfo(name = "lat")
    val lat: Double? = null,
    @ColumnInfo(name = "lon")
    val lon: Double? = null,
) {
    fun toCity() =
        City(id = id, cityNameAr = cityNameAr, cityNameEn = cityNameEn, lat = lat, lon = lon)
}