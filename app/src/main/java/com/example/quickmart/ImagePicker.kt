package com.example.quickmart

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.io.ByteArrayOutputStream
import java.util.UUID


@Composable
fun ImagePicker() : Uri{
    var pickedImage by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current

    val getContent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
//        onResult = {uri: Uri? ->
//            uri?.let {
//                pickedImage.value = saveImageToInterStorage(context,it)
//            }
//        }
    ) {uri: Uri? ->
        uri?.let {
            pickedImage = it
//            Log.d("TAG5",pickedImage)
        }
    }

//    Image(
//        painter = rememberAsyncImagePainter(File(context.filesDir,"af8b2e50-a5c0-484c-b0bb-9f471dc931ab.jpg")),
//        contentDescription = null
//    )

//    if(pickedImage.isEmpty()) {
//        SideEffect {
//            getContent.launch("image/*")
////            return@SideEffect
//        }
//    }

//    Log.d("TAG1",pickedImage)

    return pickedImage!!
}

//@Composable
//fun PickImage() : Uri {
//    var selectedImageUri by remember {
//        mutableStateOf<Uri?>(null)
//    }
//
//    val photoPickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia(),
//        onResult = { uri  -> selectedImageUri = uri})
//
//
//}

fun saveImageToInterStorage(context: Context, uri: Uri?): String {

    val fileName = UUID.randomUUID().toString() + ".jpg"
    val inputStream = context.contentResolver.openInputStream(uri!!)
    val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)

    inputStream?.use {input ->
        outputStream.use {output ->
            input.copyTo(output)
        }
    }

    return fileName
}


fun uriToBitmap(uri: Uri, context: Context) : Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}