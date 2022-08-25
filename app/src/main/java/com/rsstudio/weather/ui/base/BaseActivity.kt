package com.rsstudio.weather.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rsstudio.weather.R
import com.rsstudio.weather.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private lateinit var binding: ActivityBaseBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base)
    }
}