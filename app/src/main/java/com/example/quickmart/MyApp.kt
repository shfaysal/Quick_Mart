package com.example.quickmart

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.memory.MemoryCache
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()


        Coil.setImageLoader {
            ImageLoader.Builder(applicationContext)
                .memoryCache { MemoryCache.Builder(applicationContext).maxSizePercent(.25).build() }
                .crossfade(true)
                .build()
        }
    }
}