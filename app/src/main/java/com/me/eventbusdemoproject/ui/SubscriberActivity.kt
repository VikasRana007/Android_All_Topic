package com.me.eventbusdemoproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.me.eventbusdemoproject.R
import com.me.eventbusdemoproject.databinding.ActivityMainBinding
import com.me.eventbusdemoproject.databinding.ActivitySubscriberBinding

class SubscriberActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubscriberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subscriber)
    }
}