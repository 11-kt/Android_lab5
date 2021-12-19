package com.example.imagedownloader

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.example.imagedownloader.URL.imageURL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.URL

class MainActivityCoroutine : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {
            lifecycleScope.launchWhenResumed {
                val picture = async(Dispatchers.IO) {
                    Log.i("Coroutine",
                        "Кор. выполняется на потоке: ${Thread.currentThread().name}")
                    BitmapFactory.decodeStream(URL(imageURL).openConnection().getInputStream())
                }.await()
                Log.i("Coroutine",
                    "Кор. выполняется на потоке: ${Thread.currentThread().name}")
                findViewById<ImageView>(R.id.imageView).setImageBitmap(picture)
            }
        }
    }
}
