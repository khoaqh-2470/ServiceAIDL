package com.example.demoservice.intent

import android.app.IntentService
import android.content.Intent
import android.util.Log

const val TAG = "nnn"

class IntentService : IntentService("IntentService") {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }
    override fun onHandleIntent(intent: Intent?) {
        for (i in 0..5){
            Thread.sleep(500)
            Log.d(TAG, "onHandleIntent: $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}