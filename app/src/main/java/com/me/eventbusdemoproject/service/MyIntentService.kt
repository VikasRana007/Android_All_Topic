package com.me.eventbusdemoproject.service

import android.app.IntentService
import android.content.Intent
import com.me.eventbusdemoproject.data.MessageEvent
import org.greenrobot.eventbus.EventBus

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        EventBus.getDefault().postSticky(MessageEvent(100, "Hello Every One :"))

//        EventBus.getDefault().post(MessageEvent(Activity.RESULT_OK,"Inflate Event Bus Result"))
    }

}