package com.example.imagedownloader

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.imagedownloader.URL.imageURL
import java.net.URL
import java.util.concurrent.Executors

object URL {
    const val imageURL = "https://sun9-63.userapi.com/impg/GgLAlDIFUsli3vqvWWfcFAuRSv0HlJ2Bl2365Q" +
            "/7A9pdNtccrs.jpg?size=2560x2560&quality=95&sign=9c45a903c0f327a69740ef24b17cf08e&typ" +
            "e=album"
}

class MainActivityES : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener{
            (application as MainApp).executorService.execute {
                Log.i("ES", "текущий поток: ${Thread.currentThread().name}")
                val img = BitmapFactory.decodeStream(URL(imageURL).openConnection().getInputStream())
                mainExecutor.execute {
                    Log.i("ES", "текущий поток: ${Thread.currentThread().name}")
                    findViewById<ImageView>(R.id.imageView).setImageBitmap(img)
                }
            }
        }
    }

}