package com.midterm.mp3application.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.midterm.mp3application.other.Constant.ACTION_RECEIVER_TO_SERVICE
import com.midterm.mp3application.other.Constant.ACTION_TO_RECEIVER

class SongReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val actionMusic = intent?.getIntExtra(ACTION_TO_RECEIVER,0)

        val intentService = Intent(context,SongService::class.java)
        intentService.putExtra(ACTION_RECEIVER_TO_SERVICE,actionMusic)
        context?.startService(intentService)
    }
}