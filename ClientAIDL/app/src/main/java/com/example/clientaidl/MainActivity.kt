package com.example.clientaidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG = "nnn"
    private val MUSIC_ACTION = "musicservicebind"
    private val MUSIC_PACKAGE = "com.example.serviceaidl"
    private var mService: IMusicService? = null
    private var mIsServiceConnected = false

    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            mService = IMusicService.Stub.asInterface(iBinder)
            mIsServiceConnected = true
            Log.d(TAG, "onServiceConnected: ")
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            mIsServiceConnected = false
            Log.d(TAG, "onServiceDisconnected: ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTVClick.setOnClickListener {
//            mService?.play()
            bindService()
            Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindService() {
        val intent = Intent(MUSIC_ACTION)
        intent.setPackage(MUSIC_PACKAGE)
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        unbindService(mServiceConnection)
        super.onDestroy()
    }
}