package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class MainActivityCoroutine : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private var startTime: Int = 0
    private var currentTime: Long = 0
    private lateinit var textSecondsElapsed: TextView
    private lateinit var preference: SharedPreferences


    private suspend fun startCounting() {
        Log.i("Thread", "Worked now from Coroutine!")
        while (true) {
            delay(1000)
            secondsElapsed = (((Date().time - currentTime) / 1000) + startTime).toInt()
            textSecondsElapsed.text = ("Seconds elapsed: $secondsElapsed")
            Log.i("Thread", "$secondsElapsed")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preference = getPreferences(MODE_PRIVATE)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        lifecycleScope.launchWhenResumed { startCounting() }
    }

    override fun onResume() {
        super.onResume()
        startTime = preference.getInt(getString(R.string.secondsElapsed), 0)
        currentTime = Date().time
    }

    override fun onPause() {
        super.onPause()
        with(preference.edit()) {
            putInt(getString(R.string.secondsElapsed), secondsElapsed)
            apply()
        }
    }

}