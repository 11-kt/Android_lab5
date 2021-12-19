package com.example.imagedownloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.bumptech.glide.Glide
import com.example.imagedownloader.URL.imageURL

class MainActivityGlide : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {
            Log.i("Glide", "We use Glide!")
            Glide
                .with(applicationContext)
                .asBitmap().load(imageURL)
                .into(findViewById(R.id.imageView))
        }
    }
}
