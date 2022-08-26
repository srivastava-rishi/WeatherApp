package com.rsstudio.weather.ui.main


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rsstudio.weather.R
import com.rsstudio.weather.databinding.ActivityMainBinding
import com.rsstudio.weather.ui.base.BaseActivity


class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}