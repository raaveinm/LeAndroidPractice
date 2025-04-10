package com.raaceinm.androidpracticals

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.raaceinm.androidpracticals.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tag = "rrr"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            binding.progressCircular.visibility = View.GONE
            startTaskSequence()
        }
    }

    private fun startTaskSequence(){
        binding.startButton.isEnabled = false
        binding.progressCircular.visibility = View.VISIBLE
        updateStatus("Starting tasks...")
        binding.dogImage.setImageDrawable(null)

        lifecycleScope.launch {
            try {
                // --- Task 1: Sequential ---
                val task1Result = runSequentialTask1()
                updateStatus("Task 1 completed: $task1Result")

                // --- Task 2: Sequential (API Call to get URL) ---
                val imageUrl = runSequentialTask2_FetchImageUrl()
                updateStatus("Task 2 completed: Fetched URL.")

                // --- Task 3: Sequential (Load Image) ---
                if (imageUrl != null) {
                    runSequentialTask3_LoadImage(imageUrl)
                    updateStatus("Task 3 completed: Image loaded.")
                } else {
                    updateStatus("Task 2 failed: Could not get image URL.")
                }

                // --- Tasks 4 & 5: Parallel ---
                updateStatus("Starting parallel Tasks 4 & 5...")
                val deferredTask4 = async(Dispatchers.Default) { runParallelTask4() }
                val deferredTask5 = async(Dispatchers.Default) { runParallelTask5() }

                val result4 = deferredTask4.await()
                val result5 = deferredTask5.await()

                updateStatus("Parallel tasks finished: Task 4 " +
                        "('$result4'), Task 5 ('$result5'). All tasks done!")

            } catch (e: Exception) {
                Log.e(tag, "An error occurred: ${e.message}", e)
                updateStatus("Error: ${e.message ?: "Unknown error"}")
            } finally {
                withContext(Dispatchers.Main) {
                    binding.startButton.isEnabled = true
                    binding.progressCircular.visibility = View.GONE
                }
            }
        }
    }

    // --- Task Implementations ---

    private suspend fun runSequentialTask1(): String {
        return withContext(Dispatchers.Default) {
            Log.d(tag, "Task 1: Started")
            delay(1000)
            Log.d(tag, "Task 1: Finished")
            "Data from Task 1"
        }
    }

    private suspend fun runSequentialTask2_FetchImageUrl(): String? {
        return withContext(Dispatchers.IO) {
            Log.d(tag, "Task 2: Started (Fetching URL)")
            try {
                val response = RetrofitInstance.api.getRandomDog()
                if (response.isSuccessful && response.body() != null) {
                    val imageUrl = response.body()!!.url
                    if (imageUrl.endsWith(".jpg", ignoreCase = true) ||
                        imageUrl.endsWith(".jpeg", ignoreCase = true) ||
                        imageUrl.endsWith(".png", ignoreCase = true) ||
                        imageUrl.endsWith(".gif", ignoreCase = true)) {
                        Log.d(tag, "Task 2: Success - URL: $imageUrl")
                        imageUrl
                    } else {
                        Log.w(tag, "Task 2: Got URL, but it might not be a direct image link: $imageUrl. Trying anyway.")
                        imageUrl
                    }
                } else {
                    Log.e(tag, "Task 2: Failed - Code: ${response.code()}, Message: ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                Log.e(tag, "Task 2: Network/Parsing Error - ${e.message}", e)
                null
            }
        }
    }

    private suspend fun runSequentialTask3_LoadImage(imageUrl: String) {
        withContext(Dispatchers.Main) {
            Log.d(tag, "Task 3: Started (Loading Image into View)")
            binding.progressCircular.visibility = View.VISIBLE
            Glide.with(this@MainActivity)
                .load(imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.e(tag, "Task 3: Glide failed to load image", e)
                        binding.progressCircular.visibility = View.GONE
                        updateStatus("Task 3 Failed: Could not load image.")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d(tag, "Task 3: Glide successfully loaded image")
                        binding.progressCircular.visibility = View.GONE
                        return false
                    }
                })
                .error(R.drawable.ic_launcher_background)
                .into(binding.dogImage)
            Log.d(tag, "Task 3: Finished (Glide request initiated)")
        }
    }

    private suspend fun runParallelTask4(): String {
        return withContext(Dispatchers.Default) {
            Log.d(tag, "Task 4 (Parallel): Started")
            delay(2500)
            Log.d(tag, "Task 4 (Parallel): Finished")
            "Result from Task 4"
        }
    }

    private suspend fun runParallelTask5(): String {
        return withContext(Dispatchers.Default) {
            Log.d(tag, "Task 5 (Parallel): Started")
            delay(1500)
            Log.d(tag, "Task 5 (Parallel): Finished")
            "Result from Task 5"
        }
    }

    private fun updateStatus(message: String) {
        Log.i(tag, "Status Update: $message")
        lifecycleScope.launch(Dispatchers.Main) {
            binding.statusTextView.text = "Status: $message"
        }
    }
}