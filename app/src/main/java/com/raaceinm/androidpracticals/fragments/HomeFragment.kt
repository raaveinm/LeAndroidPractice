package com.raaceinm.androidpracticals.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.raaceinm.androidpracticals.activities.FilesTestActivity
import com.raaceinm.androidpracticals.Tools.Sterilization
import com.raaceinm.androidpracticals.Tools.Vid
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.VideoView
import com.raaceinm.androidpracticals.R

class HomeFragment : Fragment() {

    private companion object {
        private const val TAG = "asd"
        private const val VideoFileNameDefault = "background.mp4"
        private const val VideoFileNameExtra = "IP.mp4"
    }

    private lateinit var videoView: VideoView
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private var vidDefault: Vid? = null
    private var vidExtra: Vid? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate fragment initialized")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView fragment initialized")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated fragment initialized")

        videoView = view.findViewById(R.id.videoView2)
        autoCompleteTextView = view.findViewById(R.id.completed_sheesh)

        context?.let { vidDefault = Vid(videoView, it, VideoFileNameDefault) }

        val rootView = view.findViewById<ViewGroup>(R.id.home)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dropMeButton: Button = view.findViewById(R.id.button)
        dropMeButton.setOnClickListener {
            Toast.makeText(requireContext(), "achtung", Toast.LENGTH_SHORT).show()

            val inputtedURL = autoCompleteTextView.text.toString()
            Log.i(TAG, "Inputted URL: $inputtedURL")

            val intent = Intent(requireContext(), FilesTestActivity::class.java)
            intent.putExtra("autoCompleteTextView", inputtedURL)
            startActivity(intent)

            val rt = Intent(requireContext(), FilesTestActivity::class.java)
            rt.putExtra("autoCompleteTextView", inputtedURL)
            startActivity(rt)
        }
    }


    override fun onStart(){
        super.onStart()
        Log.i(TAG, "onStart fragment initialized")
        vidDefault?.playVideo(VideoFileNameDefault)
    }


    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume fragment initialized")

        val arguments = activity?.intent?.extras

        Thread {
            context?.let { ctx ->
                if (arguments != null) {
                    val phone = Sterilization.getPhone()
                    val email = Sterilization.getEmail()
                    val password = Sterilization.getPassword()
                    val name = Sterilization.getName()
                    val age = Sterilization.getAge()

                    if (name != null) {
                        // Ensure Vid instances are created if needed
                        if (vidDefault == null) vidDefault = Vid(videoView, ctx, VideoFileNameDefault)
                        vidDefault?.clearVideoCache(VideoFileNameDefault)
                        vidExtra = Vid(videoView, ctx, VideoFileNameExtra)
                        vidExtra?.playVideo(VideoFileNameExtra)

                        activity?.runOnUiThread {
                            autoCompleteTextView.visibility = View.GONE
                            Toast.makeText(ctx, "data has been stolen successful, dear $name",
                                Toast.LENGTH_LONG).show()
                        }

                        Log.v("STOLEN DATA", "NAME $name")
                        Log.v("STOLEN DATA", "PHONE $phone")
                        Log.v("STOLEN DATA", "EMAIL $email")
                        Log.v("STOLEN DATA", "PASSWORD $password")
                        Log.v("STOLEN DATA", "AGE $age")
                    } else {
                        // Ensure default video is playing if name is null but args exist
                        if (vidDefault == null) vidDefault = Vid(videoView, ctx, VideoFileNameDefault)
                        vidDefault?.playVideo(VideoFileNameDefault)
                    }
                } else {
                    if (vidDefault == null) vidDefault = Vid(videoView, ctx, VideoFileNameDefault)
                    vidDefault?.playVideo(VideoFileNameDefault)
                }
            }
        }.start()
    }

    override fun onPause(){
        super.onPause()
        Log.i(TAG, "onPause fragment initialized")
        vidDefault?.clearVideoCache(VideoFileNameDefault)
        vidExtra?.clearVideoCache(VideoFileNameExtra)
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop fragment initialized")
        context?.let {
            vidDefault?.clearVideoCache(VideoFileNameDefault)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView fragment initialized")
        context?.let {
            vidDefault?.clearVideoCache(VideoFileNameDefault)
            vidExtra?.clearVideoCache(VideoFileNameExtra)
        }
        vidDefault = null
        vidExtra = null
    }
}