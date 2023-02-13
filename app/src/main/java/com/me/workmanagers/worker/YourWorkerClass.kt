package com.me.workmanagers.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class YourWorkerClass(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // Your work here.
        notificationDialog()
        // Your task result
        return Result.success()
    }

    private fun notificationDialog() {
        Thread {
            for (i in 0..100) {
                try {
                    Thread.sleep(50)
                    println("Task is running in Back ground. . .")
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}