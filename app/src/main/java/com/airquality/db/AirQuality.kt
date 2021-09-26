package com.airquality.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "quality")
data class AirQuality(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "city") @SerialName("city") val city: String? = null,
    @ColumnInfo(name = "aqi") @SerialName("aqi") val aqiValue: String? = null,
    @ColumnInfo(name = "time") var time: Long? = 0
)