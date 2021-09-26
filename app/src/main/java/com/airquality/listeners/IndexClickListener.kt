package com.airquality.listeners

import com.airquality.db.AirQuality

interface IndexClickListener {
    fun onIndexClicked(quality: AirQuality)
}