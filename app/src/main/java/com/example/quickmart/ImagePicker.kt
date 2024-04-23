package com.example.quickmart

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.UUID


@Composable
fun ImagePicker() : String{
    val pickedImage = remember {
        mutableStateOf("")
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
            pickedImage.value = saveImageToInterStorage(context, it)
            Log.d("TAG5",pickedImage.value)
        }
    }

//    Image(
//        painter = rememberAsyncImagePainter(File(context.filesDir,"af8b2e50-a5c0-484c-b0bb-9f471dc931ab.jpg")),
//        contentDescription = null
//    )

    if(pickedImage.value.isEmpty()) {
        SideEffect {
            getContent.launch("image/*")
//            return@SideEffect
        }
    }

    Log.d("TAG1",pickedImage.value)

    return pickedImage.value
}

fun saveImageToInterStorage(context: Context, uri: Uri): String {
    val fileName = UUID.randomUUID().toString() + ".jpg"
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)

    inputStream?.use {input ->
        outputStream.use {output ->
            input.copyTo(output)
        }
    }

    return fileName
}
