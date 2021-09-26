package com.airquality.viewmodels

import androidx.lifecycle.*
import com.airquality.db.AirQuality
import com.airquality.extensions.toLiveData
import com.airquality.repo.APIRepository
import com.airquality.repo.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dbRepository: DbRepository,
    private val apiRepository: APIRepository
) : ViewModel() {

    val qualityList: LiveData<List<AirQuality>> = dbRepository.getAllIndexes().asLiveData()
    lateinit var cityIndexLiveData: LiveData<List<AirQuality>>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.connect()
            apiRepository.events.collect {
                for (item in it) {
                    item.time = System.currentTimeMillis()
                    dbRepository.insertAirQuality(item)
                }
            }
        }
    }

    fun getCityIndex(city: String) {
        cityIndexLiveData = dbRepository.getIndexByCity(city).asLiveData()
    }

    fun delete() {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.delete()
        }
    }
}