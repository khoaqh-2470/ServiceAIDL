package com.example.demoservice.workmanager

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.demoservice.R
import kotlinx.android.synthetic.main.activity_work.*
import java.util.concurrent.TimeUnit

class WorkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        buttonMyWork.setOnClickListener {
            val myConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true) // check < 10%
                .build()

            val myWork = OneTimeWorkRequest.Builder(MyWork::class.java)
                .setConstraints(myConstraints)
                .setInitialDelay(5000, TimeUnit.MILLISECONDS)
                .build()

            WorkManager
                .getInstance(this)
                .enqueue(myWork)

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                PeriodicWorkRequest.Builder(MyWork::class.java, 100000, TimeUnit.MILLISECONDS)
//            }

        }

        buttonStop.setOnClickListener {

            val workA = OneTimeWorkRequest.Builder(WorkA::class.java).build()
            val workB = OneTimeWorkRequest.Builder(WorkB::class.java).build()
            val workC = OneTimeWorkRequest.Builder(WorkC::class.java).build()

            WorkManager
                .getInstance(this)
                .beginWith(workA)
                .then(workB)
                .then(workC)
                .enqueue()
        }
    }
}