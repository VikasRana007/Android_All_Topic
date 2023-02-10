package com.me.eventbusdemoproject.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.me.eventbusdemoproject.R
import com.me.eventbusdemoproject.data.MessageEvent
import com.me.eventbusdemoproject.data.ResultData
import com.me.eventbusdemoproject.databinding.ActivityMainBinding
import com.me.eventbusdemoproject.service.Addition
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
            if(TextUtils.isEmpty(binding.stickyEvent.text) && TextUtils.isEmpty(binding.stickyEvent2.text)){
                Toast.makeText(this@MainActivity, "Please Add Sum Amount" , Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this@MainActivity, Addition::class.java)
                intent.putExtra("num1", binding.stickyEvent.text.toString().toInt())
                intent.putExtra("num2", binding.stickyEvent2.text.toString().toInt())
                startService(intent)
                Toast.makeText(this@MainActivity, "Addition Started" , Toast.LENGTH_LONG).show()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event : ResultData){
        println("Sum is Here : "+event.sum)
      binding.result.setText("""${event.sum}""")
        startActivity(Intent(this@MainActivity, SubscriberActivity::class.java))
    }

//    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
//    fun onEvent(event: MessageEvent){
//        binding.stickyData = event.message
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