package com.airquality.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.airquality.R
import com.airquality.databinding.FragmentChartBinding
import com.airquality.db.AirQuality
import com.airquality.extensions.getCompatColor
import com.airquality.viewbinding.viewBinding
import com.airquality.viewmodels.MainViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChartFragment : Fragment(R.layout.fragment_chart) {

    private val _binding: FragmentChartBinding by viewBinding()
    private val _mainViewModel: MainViewModel by viewModels()
    private var city: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("requestKey") { key, bundle ->
            city = bundle.getString("bundleKey").toString()
            callApi()
            updateUI()
        }
    }

    private fun callApi() {
        city?.let {
            _mainViewModel.getCityIndex(it)
            _mainViewModel.cityIndexLiveData.observe(viewLifecycleOwner, { result ->
                updateChart(result)
            })
        }
    }

    private fun updateChart(list: List<AirQuality>) {
        val dataPoints = mutableListOf<Entry>()
        var count = 1
        for (item in list) {
            dataPoints.add(Entry(count.toFloat(), item.aqiValue?.toFloat() ?: 0f))
            count++
        }

        val dataset = LineDataSet(dataPoints, city)
        dataset.setDrawFilled(true)
        dataset.lineWidth = 2f
        dataset.fillColor = requireContext().getCompatColor(R.color.teal_200)
        dataset.color = requireContext().getCompatColor(R.color.purple_200)
        dataset.fillAlpha = 255
        _binding.chart.data = LineData(dataset)
        _binding.chart.notifyDataSetChanged()
        _binding.chart.invalidate()
    }

    private fun updateUI() {
        val xAxis: XAxis = _binding.chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawLabels(true)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.granularity = 1F

        _binding.chart.setTouchEnabled(true)
        _binding.chart.setPinchZoom(false)
        _binding.chart.axisRight.isEnabled = false
        _binding.chart.isDoubleTapToZoomEnabled = false
        _binding.chart.setVisibleXRangeMaximum(100F)
        _binding.chart.moveViewToX(10F)
        val dataPoints = mutableListOf<Entry>()
        val dataset = LineDataSet(dataPoints, city)
        dataset.lineWidth = 2f
        dataset.fillColor = requireContext().getCompatColor(R.color.teal_200)
        dataset.color = requireContext().getCompatColor(R.color.purple_200)
        dataset.fillAlpha = 255
        _binding.chart.data = LineData(dataset)
        _binding.loading.visibility = View.GONE
        _binding.chart.visibility = View.VISIBLE
    }

}