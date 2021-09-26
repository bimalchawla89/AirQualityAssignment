package com.airquality.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airquality.databinding.IndexListItemBinding
import com.airquality.db.AirQuality
import com.airquality.extensions.setIndexColor
import com.airquality.listeners.IndexClickListener
import com.airquality.viewbinding.BindingViewHolder
import com.airquality.viewbinding.createBindingViewHolder

typealias QualityViewHolder = BindingViewHolder<IndexListItemBinding>

class QualityAdapter(private val indexClickListener: IndexClickListener) : RecyclerView.Adapter<QualityViewHolder>() {

    private var indexList = listOf<AirQuality>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QualityViewHolder {
        return createBindingViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return indexList.size
    }

    fun updateList(list: List<AirQuality>) {
        this.indexList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: QualityViewHolder, position: Int) {
        val item = indexList[position]
        holder.binding.quality = item
        holder.binding.executePendingBindings()
        holder.binding.tvIndex.setIndexColor(item.aqiValue)

        holder.binding.tvCity.setOnClickListener {
            indexClickListener.onIndexClicked(item)
        }
    }
}