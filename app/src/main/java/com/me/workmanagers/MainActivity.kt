package com.me.workmanagers

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.me.workmanagers.databinding.ActivityMainBinding
import com.me.workmanagers.worker.YourWorkerClass
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    /**
     * Two types of work request in the work managers
     * 1. OneTimeWorkRequest - Runs the task only once
     * 2. PeriodicWorkTimeRequest - Runs the task after a certain time interval.
     */

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        clickListener()
    }

    private fun  clickListener(){
        binding.apply {
            val shake: Animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.shake_horizontal)
            startWorkManager.startAnimation(shake)
            startWorkManager.setOnClickListener{
                requestBuilder()
            }
        }
    }

    @SuppressLint("IdleBatteryChargingConstraints")
    private fun requestBuilder() {
        //   customization of task
        val myConstraints = Constraints.Builder()
            .setRequiresDeviceIdle(true) //checks whether device should be idle for the WorkRequest to run
            .setRequiresCharging(true) //checks whether device should be charging for the WorkRequest to run
            .setRequiredNetworkType(NetworkType.CONNECTED) //checks whether device should have Network Connection
            .setRequiresBatteryNotLow(true) // checks whether device battery should have a specific level to run the work request
            .setRequiresStorageNotLow(true) // checks whether device storage should have a specific level to run the work request
            .build()
        // defined work request here
        val yourWorkRequest = OneTimeWorkRequestBuilder<YourWorkerClass>()
            // add above customization in the above request
            .setConstraints(myConstraints)
            // To set initial delay
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()


        //now schedule the task using
        WorkManager.getInstance(this).enqueue(yourWorkRequest)

        checkStatusWorkManager(yourWorkRequest)
        // We can also set the Periodic Task which will run after a certain time interval.
        // To run a workRequest which runs periodically we use,
//        val yourPeriodicWorkRequest =
//            PeriodicWorkRequestBuilder<YourPeriodicWorkerClass>(1, TimeUnit.HOURS)
//                .setConstraints(myConstraints)
//                .build()
    }

    private fun checkStatusWorkManager(yourWorkRequest: OneTimeWorkRequest) {
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(yourWorkRequest.id)
            .observe(this) { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    Toast.makeText(this, "Back Ground Task Completed !!!", Toast.LENGTH_LONG).show()
                }
            }
       }
}