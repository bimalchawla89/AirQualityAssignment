package com.airquality.dao

import androidx.room.*
import com.airquality.db.AirQuality
import kotlinx.coroutines.flow.Flow


@Dao
interface CityDao {
    @Query("SELECT * FROM quality")
    fun getAllIndexes(): Flow<List<AirQuality>>

    @Query("SELECT * FROM quality WHERE city=:city ORDER BY time")
    fun getIndexByCity(city: String): Flow<List<AirQuality>>

    @Query("DELETE FROM quality")
    suspend fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirQuality(airQuality: AirQuality)
}