package com.example.demoservice.background

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class BackgroundService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(
            this,
            "onStartCommand ${intent?.getStringExtra("intent")}",
            Toast.LENGTH_SHORT
        ).show()
        return START_STICKY_COMPATIBILITY
    }
}