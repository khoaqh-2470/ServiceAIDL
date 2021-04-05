package com.example.demoservice.bound

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.demoservice.R
import kotlinx.android.synthetic.main.activity_bound.*

const val TAG = "nnn"

class BoundActivity : AppCompatActivity() {

    private lateinit var boundService: BoundService

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            boundService = service.let {
                it as BoundService.MyBinder
                it.getService()
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound)
        bindService()

        buttonAdd.setOnClickListener {
            textViewCount.text = boundService.addI().toString()
        }
        buttonRemove.setOnClickListener {
            textViewCount.text = boundService.removeI().toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService()
    }

    private fun bindService() {
        Intent(this, BoundService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun unbindService() {
        unbindService(serviceConnection)
    }
}