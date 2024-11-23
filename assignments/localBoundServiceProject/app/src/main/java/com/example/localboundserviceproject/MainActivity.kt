package com.example.localboundserviceproject

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.localboundserviceproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var stopwatchService: StopwatchService? = null
    private var isBound = false
    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as StopwatchService.LocalBinder
            stopwatchService = binder.getService()
            isBound = true
            updateUI()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener { stopwatchService?.startTimer() }
        binding.pauseButton.setOnClickListener { stopwatchService?.pauseTimer() }
        binding.resetButton.setOnClickListener { stopwatchService?.resetTimer() }
        }

    override fun onStart() {
        super.onStart()
        //bind to the service
        Intent(this, StopwatchService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
        handler.removeCallbacks(updateUITask)
    }

    private fun updateUI() {
        binding.textView.postDelayed({
            if (isBound && stopwatchService != null) {
                // Retrieve the elapsed time from the service
                val elapsedTime = stopwatchService?.getCurrentTime() ?: 0L
                // Calculate hours, minutes, and seconds from elapsed time
                val seconds = (elapsedTime / 1000) % 60
                val minutes = (elapsedTime / (1000 * 60)) % 60
                val hours = (elapsedTime / (1000 * 60 * 60)) % 24
                // Update the UI with the formatted time
                binding.textView.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            }
            // Re-run updateElapsedTime every second
            updateUI()
        }, 1000)
    }
    private val updateUITask = Runnable { updateUI() }
    }
