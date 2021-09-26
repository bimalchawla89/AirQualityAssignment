package com.airquality.repo

import com.airquality.dao.CityDao
import com.airquality.db.AirQuality
import javax.inject.Inject

class DbRepository @Inject constructor(
    private val cityDao: CityDao
) {

    fun getAllIndexes() = cityDao.getAllIndexes()

    fun getIndexByCity(city: String) = cityDao.getIndexByCity(city)

    suspend fun insertAirQuality(airQuality: AirQuality) = cityDao.insertAirQuality(airQuality)

    suspend fun delete() = cityDao.delete()

}