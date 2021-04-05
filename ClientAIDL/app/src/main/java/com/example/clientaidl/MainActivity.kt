package com.example.clientaidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

const val MUSIC_ACTION = "musicservicebind"
const val MUSIC_PACKAGE = "com.example.serviceaidl"

const val MESSENGER_ACTION = "messengerbind"

class MainActivity : AppCompatActivity() {
    val TAG = "nnn"

    private var iMusicService: IMusicService? = null
    private var iMessengerService: Messenger? = null
    private var isAIDL = false
    private var isMessenger = false

    private val serviceConnectionAIDL: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            iMusicService = IMusicService.Stub.asInterface(iBinder)
            isAIDL = true
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
        }
    }

    private val serviceConnectionMessenger: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            iMessengerService = Messenger(iBinder)
            isMessenger = true
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onEventAIDL()
        onEventMessenger()
    }

    private fun onEventMessenger() {
        buttonConnectMessenger.setOnClickListener {
            bindService(serviceConnectionMessenger, MESSENGER_ACTION)
        }
    }

    private fun bindService(conn: ServiceConnection, action: String) {
        val intent = Intent(action)
        intent.setPackage(MUSIC_PACKAGE)
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
    }

    private fun onEventAIDL() {
        buttonConnectAIDL.setOnClickListener {
            bindService(serviceConnectionAIDL, MUSIC_ACTION)
        }
        buttonSongName.setOnClickListener {
            Toast.makeText(this, "SongName " + iMusicService?.songName, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        if (isAIDL) {
            isAIDL = false
            unbindService(serviceConnectionAIDL)
        }

        if (isMessenger) {
            isMessenger = false
            unbindService(serviceConnectionMessenger)
        }
        super.onDestroy()
    }

    fun say(view: View) {
        if (!isMessenger) return

        val msg: Message = Message.obtain(null, edtMessenger.text.toString().toInt(), 0, 0)
        try {
            iMessengerService?.send(msg)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }
}