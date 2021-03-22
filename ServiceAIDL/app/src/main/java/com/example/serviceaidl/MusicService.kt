package com.example.serviceaidl

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.serviceaidl.IMusicService.Stub

class MusicService : Service() {

    val TAG = "nnn"
    private val NOTIFICATION_ID = 1
    private lateinit var mNotification: Notification

    var mBinder = object : Stub() {
        override fun getSongName(): String {
            Log.d(TAG, "getSongName: ")
            return "khoa"
        }

        override fun changeMediaStatus() {
            Log.d(TAG, "changeMediaStatus: ")
        }

        override fun playSong() {
            Log.d(TAG, "playSong: ")
        }

        override fun play() {
            Log.d(TAG, "play: ")
        }

        override fun pause() {
            Log.d(TAG, "pause: ")
        }

        override fun getCurrentDuration(): Int {
            Log.d(TAG, "getCurrentDuration: ")
            return 0
        }

        override fun getTotalDuration(): Int {
            Log.d(TAG, "getTotalDuration: ")
            return 0
        }


    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: ")
        startNotification("ok")
        return mBinder
    }

    fun startNotification(string: String) {
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                "khoa",
                "My Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )
        val notification: Notification = NotificationCompat.Builder(this, "khoa")
            .setContentTitle("A service is running in the background")
            .setContentText(string).build()
        startForeground(1, notification)
    }
}