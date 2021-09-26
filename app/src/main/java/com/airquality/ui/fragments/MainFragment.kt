package com.airquality.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.airquality.R
import com.airquality.adapters.QualityAdapter
import com.airquality.databinding.FragmentMainBinding
import com.airquality.db.AirQuality
import com.airquality.listeners.IndexClickListener
import com.airquality.viewbinding.viewBinding
import com.airquality.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), IndexClickListener {

    private val _binding: FragmentMainBinding by viewBinding()
    private val _mainViewModel: MainViewModel by viewModels()
    private val _qualityAdapter by lazy { QualityAdapter(this) }
    private val indexList = mutableListOf<AirQuality>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        callApi()
    }

    private fun setUI() {
        _binding.rvIndex.adapter = _qualityAdapter
    }

    private fun callApi() {
        _mainViewModel.delete()
        _mainViewModel.qualityList.observe(viewLifecycleOwner, {
            _qualityAdapter.updateList(getUpdatedList(it))
        })
    }

    private fun getUpdatedList(newList: List<AirQuality>): List<AirQuality> {
        if (newList.isNotEmpty()) {
            for (item in newList) {
                var found = false
                for (previousItem in indexList) {
                    if (item.city == previousItem.city) {
                        found = true
                        indexList[indexList.indexOf(previousItem)] = AirQuality(
                            city = item.city,
                            aqiValue = item.aqiValue,
                            time = System.currentTimeMillis()
                        )
                    }
                }
                if (!found) {
                    indexList.add(item)
                }
            }
        }
        return indexList
    }

    override fun onIndexClicked(quality: AirQuality) {
        setFragmentResult("requestKey", bundleOf("bundleKey" to quality.city))
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, ChartFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}