package com.example.demoservice.foreground

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.demoservice.R
import kotlinx.android.synthetic.main.activity_foreground.*

class ForegroundActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground)

        buttonStart.setOnClickListener {
            Intent(this, ForegroundService::class.java).also {
                startService(it)
            }
        }
        buttonStop.setOnClickListener {
            Intent(this, ForegroundService::class.java).also {
                stopService(it)
            }
        }
    }
}