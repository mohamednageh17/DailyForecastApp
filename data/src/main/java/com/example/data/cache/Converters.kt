package com.example.data.cache

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.domain.cache.CityEntity
import com.example.domain.cache.ForecastEntityList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun StringToCity(value: String?): CityEntity? {
        return if (value.isNullOrEmpty()) null
        else try {
            Gson().fromJson(value, CityEntity::class.java)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            null
        }
    }

    @TypeConverter
    fun CityToString(value: CityEntity?): String? {
        return if (value == null) null
        else try {
            Gson().toJson(value, CityEntity::class.java)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            null
        }
    }

    @TypeConverter
    fun fromString(value: String): List<ForecastEntityList>? {
        val listType = object : TypeToken<List<ForecastEntityList>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ForecastEntityList>?): String {
        return Gson().toJson(list)
    }

}