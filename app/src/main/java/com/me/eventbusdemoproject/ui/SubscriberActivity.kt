package com.me.eventbusdemoproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.me.eventbusdemoproject.R
import com.me.eventbusdemoproject.data.ResultData
import com.me.eventbusdemoproject.databinding.ActivitySubscriberBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SubscriberActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubscriberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subscriber)
    }

// Event bus can also be used StartServiceForResult & StartActivityForResult
// cause these are the deprecated function in above android 10

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event : ResultData){
        println("Sum is Here : "+event.sum)
        binding.result.setText("""${event.sum}""")
        EventBus.getDefault().removeStickyEvent(event)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }


}