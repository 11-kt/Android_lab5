package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.random.Random

class MainActivityThread : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private var startTime: Int = 0
    private var currentTime: Long = 0
    private lateinit var textSecondsElapsed: TextView
    private lateinit var preference: SharedPreferences

    private lateinit var backgroundThread: Thread
    private val threadRun = Runnable {
        try {
            Log.i("Thread", "Worked now from Thread!")
            while (true) {
                Thread.sleep(1000)
                secondsElapsed = (((Date().time - currentTime) / 1000) + startTime).toInt()
                textSecondsElapsed.post {
                    textSecondsElapsed.text = ("Seconds elapsed: $secondsElapsed")
                }
                Log.i("Thread", "$secondsElapsed")
            }
        } catch (E: InterruptedException) { Log.i("Thread", "Thread Interrupted!") }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preference = getPreferences(MODE_PRIVATE)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onResume() {
        super.onResume()
        startTime = preference.getInt(getString(R.string.secondsElapsed), 0)
        currentTime = Date().time
        backgroundThread = Thread(threadRun)
        backgroundThread.start()
    }

    override fun onPause() {
        super.onPause()
        backgroundThread.interrupt()
        Log.i("Thread", "Thread Interrupt!")
        with(preference.edit()) {
            putInt(getString(R.string.secondsElapsed), secondsElapsed)
            apply()
        }
    }
}