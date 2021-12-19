package com.example.imagedownloader

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainApp: Application() {

    val executorService: ExecutorService = Executors.newSingleThreadExecutor()

}