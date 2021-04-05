package com.example.clientaidl

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.clientaidl.IMusicService.Stub

class MusicService : Service() {

    val TAG = "nnn"

    var mBinder = object : Stub() {
        override fun getSongName(): String {
            Log.d(TAG, "getSongName")
            startNotification("getSongName")
            return "sun"
        }

        override fun changeMediaStatus() {
            Log.d(TAG, "changeMediaStatus")
            startNotification("changeMediaStatus")
        }

        override fun playSong() {
            Log.d(TAG, "playSong")
            startNotification("playSong")
        }

        override fun play() {
            Log.d(TAG, "play")
            startNotification("play")
        }

        override fun pause() {
            Log.d(TAG, "pause")
            startNotification("pause")
        }

        override fun getCurrentDuration(): Int {
            Log.d(TAG, "getCurrentDuration")
            startNotification("getCurrentDuration")
            return 0
        }

        override fun getTotalDuration(): Int {
            Log.d(TAG, "getTotalDuration")
            startNotification("getTotalDuration")
            return 0
        }


    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        startNotification("Connect")
        return mBinder
    }

    private fun startNotification(content: String) {
        val intent =
            Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("stop", true)
        val pendingIntent = PendingIntent.getService(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(channel)
        }
        val notification =
            NotificationCompat.Builder(applicationContext, "default")
                .setContentTitle("Service")
                .setContentText(content)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
        startForeground(1, notification.build())
    }
}