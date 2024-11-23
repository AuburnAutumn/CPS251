package com.example.localboundserviceproject

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.SystemClock

class StopwatchService : Service() {

    private val binder = LocalBinder()
    private var currentTime = 0L
    private var running = false
    var startTime = 0L

    private val handler = Handler(Looper.getMainLooper())

    inner class LocalBinder : Binder() {
        fun getService(): StopwatchService = this@StopwatchService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun startTimer() {
        if (!running) {
            running = true
            //Makes sure stopwatch runs from where paused instead of from when the app started
            startTime = SystemClock.elapsedRealtime() - currentTime
            handler.post(updateRunnable)
        }
    }

    fun pauseTimer() {
        if (running) {
            running = false
            handler.removeCallbacks(updateRunnable)
        }
    }

    fun resetTimer() {
        running = false
        handler.removeCallbacks(updateRunnable)
        //Sets currentTime to 0 again
        currentTime = 0L
    }

    fun getCurrentTime(): Long {
        return currentTime
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateRunnable)
    }

    private val updateRunnable = object : Runnable {
        override fun run() {
            if (running) {
                val timeNow = SystemClock.elapsedRealtime()
                //Calculates how many seconds it's been
                currentTime = timeNow - startTime
                handler.postDelayed(this, 100)
            }
        }
    }

}