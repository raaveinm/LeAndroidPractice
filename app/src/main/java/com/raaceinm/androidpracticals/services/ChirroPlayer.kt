package com.raaceinm.androidpracticals.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.raaceinm.androidpracticals.R // Assuming R class is accessible

class ChirroPlayer : Service() {

    private val TAG = "ChirroPlayer"
    private lateinit var player: ExoPlayer

    // --- Define Actions and Extras ---
    companion object {
        const val ACTION_PLAY = "com.raaceinm.androidpracticals.ACTION_PLAY"
        const val ACTION_PAUSE = "com.raaceinm.androidpracticals.ACTION_PAUSE"
        const val EXTRA_MEDIA_RES_ID = "com.raaceinm.androidpracticals.EXTRA_MEDIA_RES_ID"
    }
    // ---------------------------------

    override fun onCreate() {
        super.onCreate()
        // Use applicationContext for safety, although 'this' should be valid here
        player = ExoPlayer.Builder(this.applicationContext).build()
        // Don't prepare here, prepare when a media item is set
        // player.prepare()
        player.playWhenReady = true // Start playing as soon as media is ready and play() is called
        player.volume = 0.5f
        Log.d(TAG, "Service Created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand Received: ${intent?.action}")

        // Handle null intent or action gracefully
        val action = intent?.action ?: return START_STICKY // Or START_NOT_STICKY

        // Default to your track if none provided, or handle error
        val mediaResId = intent.getIntExtra(EXTRA_MEDIA_RES_ID, R.raw.faint)

        when (action) {
            ACTION_PLAY -> {
                // Only resolve if we have a valid resource ID (could check if it's not the default)
                commandResolve("play", mediaResId)
            }
            ACTION_PAUSE -> {
                commandResolve("pause", mediaResId) // ResId might not be needed for pause
            }
            else -> Log.w(TAG, "Unknown action received: $action")
        }

        return START_STICKY // Keep service running
    }

    // Make this private or internal if only called from within the service
    private fun commandResolve(playerCommand: String, mediaItemRes: Int) {
        // Use applicationContext here for safety when creating the URI
        val currentPackageName = applicationContext.packageName
        val uri = "android.resource://$currentPackageName/$mediaItemRes"
        Log.d(TAG, "commandResolve: $playerCommand, Uri: $uri")


        if (uri.isNotEmpty()) {
            val mediaItem = MediaItem.fromUri(uri)

            when (playerCommand) {
                "play" -> {
                    // Check if the current item is different or if not prepared
                    val currentMediaItem = player.currentMediaItem
                    if (currentMediaItem == null || currentMediaItem.mediaId != mediaItem.mediaId || !player.isCommandAvailable(ExoPlayer.COMMAND_PLAY_PAUSE)) {
                        Log.d(TAG, "Setting new media item and preparing.")
                        player.setMediaItem(mediaItem)
                        player.prepare() // Prepare must be called after setMediaItem
                    } else {
                        Log.d(TAG,"Media item already set, just playing.")
                    }
                    player.play() // Call play regardless
                }
                "pause" -> {
                    if (player.isPlaying) {
                        Log.d(TAG,"Pausing player.")
                        player.pause()
                    } else {
                        Log.d(TAG,"Player already paused.")
                    }
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
        // Check if player is initialized before releasing
        if (::player.isInitialized) {
            player.release()
            Log.d(TAG, "Player Released")
        }
    }

    // Return null for non-bindable service (unless you implement binding later)
    override fun onBind(intent: Intent): IBinder? = null
}