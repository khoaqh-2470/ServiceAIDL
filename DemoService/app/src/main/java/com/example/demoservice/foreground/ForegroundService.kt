package com.example.demoservice.foreground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.demoservice.R

const val TAG = "nnn"

class ForegroundService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        sendNotification("title", "ForegroundService")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getBooleanExtra("stop", false)?.let {
            if (it) {
                stopSelf()
//                stopForeground(true)
            }
        }
        Log.d(TAG, "onStartCommand: ")
        return START_NOT_STICKY
    }

    private fun sendNotification(title: String, message: String) {
        val intent =
            Intent(applicationContext, ForegroundService::class.java)
        intent.putExtra("stop", true)
        val pendingIntent = PendingIntent.getService(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        val notification =
            NotificationCompat.Builder(applicationContext, "default")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
        startForeground(1, notification.build())
    }
}