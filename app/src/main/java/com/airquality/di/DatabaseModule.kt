package com.airquality.di

import android.content.Context
import androidx.room.Room
import com.airquality.AirQualityApp
import com.airquality.dao.CityDao
import com.airquality.db.AirQualityDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideImageUploadDao(airQualityDatabase: AirQualityDatabase): CityDao {
        return airQualityDatabase.cityDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AirQualityDatabase {
        return Room.databaseBuilder(
            appContext,
            AirQualityDatabase::class.java,
            "AirQuality"
        ).build()
    }
}