package com.example.demoservice.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkB(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Thread.sleep(5000)
        Log.d("nnn", "WorkB")
        return Result.success()
    }
}