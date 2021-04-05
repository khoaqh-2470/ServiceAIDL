package com.example.demoservice.bound

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BoundService : Service() {

    private var i: Int = 0
    private val binder: IBinder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    inner class MyBinder : Binder() {
        fun getService(): BoundService = this@BoundService
    }

    fun addI(): Int {
        i += 1
        return i
    }

    fun removeI(): Int {
        i -= 1
        return i
    }
}