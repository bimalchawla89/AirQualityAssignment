package com.airquality.utils

import android.text.format.DateUtils
import kotlinx.serialization.json.Json
import java.text.ParseException

object CommonFunctions {

    val json by lazy {
        Json {
            isLenient = true; ignoreUnknownKeys = true; coerceInputValues =
            true; allowSpecialFloatingPointValues = true
        }
    }

    fun convertedTime(time: Long): String {
        return try {
            DateUtils.getRelativeTimeSpanString(time, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString()
        } catch (e: ParseException) {
            ""
        }
    }
}