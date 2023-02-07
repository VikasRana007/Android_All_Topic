package com.me.eventbusdemoproject.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.me.eventbusdemoproject.R
import com.me.eventbusdemoproject.data.MessageEvent
import com.me.eventbusdemoproject.databinding.ActivityMainBinding
import com.me.eventbusdemoproject.service.MyIntentService
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.generateEvent.setOnClickListener{
            val intent = Intent(this@MainActivity, MyIntentService::class.java)
            startService(intent)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event : MessageEvent){
        Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_LONG).show()
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onEvent(event: MessageEvent){
        binding.stickyData = event.message
    }

//    @Subscribe
//    private fun handleSomethingElse(){
//
//    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

}