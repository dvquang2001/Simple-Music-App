package com.midterm.mp3application.service

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.midterm.mp3application.R
import com.midterm.mp3application.base.CHANNEL_ID
import com.midterm.mp3application.data.*
import com.midterm.mp3application.view.activity.MainActivity

class SoundService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var selectedSong: Song

    private val ACTION_PAUSE = 1
    private val ACTION_RESUME = 2
    private val ACTION_CLEAR = 3

    private var isPlaying = false

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val bundle = intent?.extras
        selectedSong = bundle?.get(KEY_SELECTED_SONG) as Song

        releaseMediaPlayer()
        startSong()
        sendNotification()

        return START_NOT_STICKY
    }

    private fun sendNotification() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val bitmap = BitmapFactory.decodeResource(resources, selectedSong.imageSrc!!)

        val remoteViews = RemoteViews(packageName, R.layout.layout_custom_notification)
        remoteViews.setTextViewText(R.id.txt_song_name_notification, selectedSong.songName)
        remoteViews.setTextViewText(R.id.txt_single_name_notification, selectedSong.singleName)
        remoteViews.setImageViewBitmap(R.id.img_song, bitmap)
        remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.ic_pause)

        if(isPlaying) {
            remoteViews.setOnClickPendingIntent(R.id.img_play_or_pause,getPendingIntent(this,ACTION_PAUSE))
            remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.ic_play)
        }
        else {
            remoteViews.setOnClickPendingIntent(R.id.img_play_or_pause,getPendingIntent(this,ACTION_RESUME))
            remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.ic_pause)
        }

        remoteViews.setOnClickPendingIntent(R.id.img_clear,getPendingIntent(this,ACTION_CLEAR))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_small_music)
                .setCustomContentView(remoteViews)
                .setSound(null)
                .setContentIntent(pendingIntent)
                .build()
            startForeground(1, notification)
        }
    }

    private fun getPendingIntent(context: Context,action: Int): PendingIntent {

    }

    private fun handleAction(action: Int) {
        when(action) {
            ACTION_PAUSE -> pauseMusic()
            ACTION_RESUME -> resumeMusic()
            ACTION_CLEAR -> clearMusic()
        }
    }

    private fun pauseMusic() {
        if(isPlaying) {
            mediaPlayer.pause()
            isPlaying = false
        }
    }

    private fun resumeMusic() {
        if(!isPlaying) {
            mediaPlayer.start()
            isPlaying = true
        }
    }

    private fun clearMusic() {
        stopSelf()
    }

    private fun releaseMediaPlayer() {
        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.release()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun startSong() {
        mediaPlayer = MediaPlayer.create(this, selectedSong.songSrc!!)
        mediaPlayer.start()
        isPlaying = true
    }

    override fun onDestroy() {
        releaseMediaPlayer()
        stopSelf()
        super.onDestroy()
    }
}