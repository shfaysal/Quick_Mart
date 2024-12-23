package com.example.quickmart.data.api

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.Base64

class Converters {

    @TypeConverter
    @RequiresApi(Build.VERSION_CODES.O)
    fun fromByteArray(byteArray: ByteArray): String {
        return Base64.getEncoder().encodeToString(byteArray)
    }

    @TypeConverter
    @RequiresApi(Build.VERSION_CODES.O)
    fun toByteArray(string: String): ByteArray {
        return Base64.getDecoder().decode(string)
    }
}