package com.raaceinm.androidpracticals.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.raaceinm.androidpracticals.R

class ChirroPlayer : Service() {

    private val TAG = "ChirroPlayer"
    private lateinit var player: ExoPlayer


    companion object {
        const val ACTION_PLAY = "com.raaceinm.androidpracticals.ACTION_PLAY"
        const val ACTION_PAUSE = "com.raaceinm.androidpracticals.ACTION_PAUSE"
        const val EXTRA_MEDIA_RES_ID = "com.raaceinm.androidpracticals.EXTRA_MEDIA_RES_ID"
    }

    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this.applicationContext).build()
        player.playWhenReady = true
        player.volume = 0.5f
        Log.d(TAG, "Service Created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand Received: ${intent?.action}")

        val action = intent?.action ?: return START_STICKY
        val mediaResId = intent.getIntExtra(EXTRA_MEDIA_RES_ID, R.raw.faint)

        when (action) {
            ACTION_PLAY -> {
                commandResolve("play", mediaResId)
            }
            ACTION_PAUSE -> {
                commandResolve("pause", mediaResId)
            }
            else -> Log.w(TAG, "Unknown action received: $action")
        }
        return START_STICKY
    }

    private fun commandResolve(playerCommand: String, mediaItemRes: Int) {
        val currentPackageName = applicationContext.packageName
        val uri = "android.resource://$currentPackageName/$mediaItemRes"
        Log.d(TAG, "commandResolve: $playerCommand, Uri: $uri")


        if (uri.isNotEmpty()) {
            val mediaItem = MediaItem.fromUri(uri)

            when (playerCommand) {
                "play" -> {
                    val currentMediaItem = player.currentMediaItem
                    if (currentMediaItem == null || currentMediaItem.mediaId != mediaItem.mediaId ||
                        !player.isCommandAvailable(ExoPlayer.COMMAND_PLAY_PAUSE)) {
                        Log.d(TAG, "Setting new media item and preparing.")
                        player.setMediaItem(mediaItem)
                        player.prepare()
                    }
                    player.play()
                }
                "pause" -> {
                    Log.d(TAG,"Pausing player.")
                    player.pause()
                }
                else -> Log.e(TAG, "Invalid internal command: $playerCommand")
            }
        } else {
            Log.e(TAG, "Uri is empty for Res ID: $mediaItemRes")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service Destroyed")
        if (::player.isInitialized) {
            player.release()
            Log.d(TAG, "Player Released")
        }
    }

    override fun onBind(intent: Intent): IBinder? = null
}