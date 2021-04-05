package com.example.clientaidl

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.widget.Toast

private const val MSG_SAY_HELLO = 1
private const val MSG_SAY_HI = 2
private const val MSG_SAY_GOODBYE = 3

class MessengerService : Service() {

    private lateinit var messenger: Messenger

    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(applicationContext, "binding", Toast.LENGTH_SHORT).show()
        messenger = Messenger(IncomingHandler(this))
        return messenger.binder
    }

    internal class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_SAY_HELLO ->
                    Toast.makeText(applicationContext, "Hello bro!", Toast.LENGTH_SHORT).show()
                MSG_SAY_HI ->
                    Toast.makeText(applicationContext, "Hi bro!", Toast.LENGTH_SHORT).show()
                MSG_SAY_GOODBYE ->
                    Toast.makeText(applicationContext, "Bye bye bro!", Toast.LENGTH_SHORT).show()
                else -> super.handleMessage(msg)
            }
        }
    }
}