package com.raaceinm.androidpracticals.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.raaceinm.androidpracticals.R
import com.raaceinm.androidpracticals.services.ChirroPlayer

class DashboardFragment : Fragment() {

    private val TAG = "DashboardFragment"
    private var isPlayingState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach - Starting Service")
        val launchIntent = Intent(requireContext(), ChirroPlayer::class.java)
        try {
            requireContext().startService(launchIntent)
        } catch (e: Exception) {
            Log.e(TAG, "Error starting service in onAttach: ${e.message}", e)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated - Setting up listener")

        val buttonPlayPause = view.findViewById<ImageButton>(R.id.ButtonPlayPause)

        updateButtonImage(buttonPlayPause, isPlayingState)


        buttonPlayPause?.setOnClickListener {
            Log.d(TAG, "Play/Pause button clicked. Current state: $isPlayingState")
            if (!isPlayingState) {
                val playIntent = Intent(requireContext(), ChirroPlayer::class.java).apply {
                    action = ChirroPlayer.ACTION_PLAY
                    putExtra(ChirroPlayer.EXTRA_MEDIA_RES_ID, R.raw.faint)
                }
                try {
                    requireContext().startService(playIntent)
                    Log.d(TAG, "Sent PLAY action to service")
                    isPlayingState = true
                    updateButtonImage(buttonPlayPause, isPlayingState)
                } catch (e: Exception) {
                    Log.e(TAG, "Error sending PLAY action: ${e.message}", e)
                }

            } else {
                val pauseIntent = Intent(requireContext(), ChirroPlayer::class.java).apply {
                    action = ChirroPlayer.ACTION_PAUSE
                }
                try {
                    requireContext().startService(pauseIntent)
                    Log.d(TAG, "Sent PAUSE action to service")
                    isPlayingState = false
                    updateButtonImage(buttonPlayPause, isPlayingState)
                } catch (e: Exception) {
                    Log.e(TAG, "Error sending PAUSE action: ${e.message}", e)
                }
            }
        }
    }

    private fun updateButtonImage(button: ImageButton?, isPlaying: Boolean) {
        button?.setImageResource(
            if (isPlaying) R.drawable.pause else R.drawable.play_arrow
        )
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }
}