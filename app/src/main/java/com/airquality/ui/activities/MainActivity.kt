package com.airquality.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.airquality.R
import com.airquality.base.BaseActivity
import com.airquality.databinding.ActivityMainBinding
import com.airquality.ui.fragments.MainFragment
import com.airquality.viewbinding.viewBinding
import com.airquality.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commitNowAllowingStateLoss()
    }
}