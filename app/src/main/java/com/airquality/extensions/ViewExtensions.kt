package com.airquality.extensions

import android.widget.TextView
import com.airquality.R
import kotlin.math.roundToInt

fun TextView.setIndexColor(index: String?) {
    val color = try {
        when (index?.toDouble()?.roundToInt()) {
            in 1..50 -> this.context.getCompatColor(R.color.good)
            in 51..100 -> this.context.getCompatColor(R.color.satisfactory)
            in 101..200 -> this.context.getCompatColor(R.color.moderate)
            in 201..300 -> this.context.getCompatColor(R.color.poor)
            in 301..400 -> this.context.getCompatColor(R.color.very_poor)
            in 401..500 -> this.context.getCompatColor(R.color.severe)
            else -> this.context.getCompatColor(R.color.black)
        }
    } catch (ex: Exception) {
        this.context.getCompatColor(R.color.black)
    }
    this.setTextColor(color)
    this.text = String.format("%.2f", index?.toDouble())
}