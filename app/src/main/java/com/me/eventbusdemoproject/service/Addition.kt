package com.me.eventbusdemoproject.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.me.eventbusdemoproject.data.ResultData
import org.greenrobot.eventbus.EventBus

class Addition : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val num1 = intent?.getIntExtra("num1", 0)
        val num12 = intent?.getIntExtra("num2", 0)
        val sum = num1!! + num12!!
        println("Sum is Here @ Service : $sum")
        EventBus.getDefault().postSticky(ResultData(sum = sum))
        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}