package com.airquality.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.airquality.dao.CityDao

@Database(
    entities = [
        AirQuality::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AirQualityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}