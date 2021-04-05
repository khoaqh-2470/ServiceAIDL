package com.example.demoservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demoservice.background.BackgroundService
import com.example.demoservice.bound.BoundActivity
import com.example.demoservice.foreground.ForegroundActivity
import com.example.demoservice.intent.IntentService
import com.example.demoservice.workmanager.WorkActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonIntentService.setOnClickListener {
            Intent(this, IntentService::class.java).also {
                startService(it)
            }
        }

        buttonWorkManager.setOnClickListener {
            Intent(this, WorkActivity::class.java).also {
                startActivity(it)
            }
        }

        buttonForeground.setOnClickListener {
            Intent(this, ForegroundActivity::class.java).also {
                startActivity(it)
            }
        }

        buttonBackground.setOnClickListener {
            Intent(this, BackgroundService::class.java).also {
                it.putExtra("intent", "intent")
                startService(it)
            }
        }
        buttonBound.setOnClickListener {
            Intent(this, BoundActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}