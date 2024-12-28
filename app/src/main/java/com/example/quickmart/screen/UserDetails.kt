package com.example.quickmart.screen

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.quickmart.ImagePicker
import com.example.quickmart.bitmapToByteArray
import com.example.quickmart.byteArrayToBitmap
import com.example.quickmart.data.database.UserDatabase
import com.example.quickmart.data.models.User
import com.example.quickmart.presentation.UserInfoGetViewModel
import com.example.quickmart.presentation.UserInfoGetViewModelFactory
import com.example.quickmart.presentation.UserViewModel
import com.example.quickmart.presentation.UserViewModelFactory
import com.example.quickmart.uriToBitmap
import kotlinx.coroutines.flow.collectLatest
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.InputStream

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun UserDetails() {

    val context = LocalContext.current


//    val user = User(0,"sazzad","mobile","dd","male",23,"sd","dsdf")

    val viewModel1: UserInfoGetViewModel = viewModel(
        factory = UserInfoGetViewModelFactory(context)
    )

    val userdetails = viewModel1.userDetails.collectAsState().value

//    Log.d("TAG", userdetails!!.name)

//    if (userdetails != null) {
//        Log.d("TAG",userdetails.name)
//    }


    LaunchedEffect(key1 = viewModel1.showErrorTestChannel) {

        viewModel1.showErrorTestChannel.collectLatest { show ->
            if (!show) {
                Toast.makeText(
                    context, "Data Loaded Unsuccessful", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

//    var userdetails : User? = null
//
//
//    GlobalScope.launch(Dispatchers.IO) {
//         userdetails = UserDatabase.getDatabase(context).userDao().getUser()
//    }


//    if (userdetails!!.name.equals(null)){
//        userdetails = user
//    }
//
//    val userdetails = user
//
//    val userdetails = user


    var isFirst by remember { mutableStateOf(true) }

    var id by remember { mutableIntStateOf(0) }

    var name by remember { mutableStateOf("") }

    var mobile by remember { mutableStateOf("") }

    var address by remember { mutableStateOf("") }

    var gender by remember { mutableStateOf("") }

    var age by remember { mutableStateOf("0") }

    var birthDate by remember { mutableStateOf("") }

    var selectedImageUri by remember { mutableStateOf<ByteArray?>(null) }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var image = remember { mutableStateOf("") }

    var upDateOrUploadButton by remember { mutableStateOf("UpLoad") }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> imageUri = uri })


    if (isFirst) {
        userdetails?.let {
            id = userdetails.id
            name = userdetails.name
            mobile = userdetails.mobile
            address = userdetails.address
            gender = userdetails.gender
            age = userdetails.age.toString()
            birthDate = userdetails.birthdate
            selectedImageUri = userdetails.photo
            upDateOrUploadButton = "UpDate"
        }
        Log.d("COUNT", "$isFirst")
        Log.d("URI", selectedImageUri.toString())
    }


    var clickedAddPhoto by remember {
        mutableStateOf(false)
    }

    var isUpdateButtonClicked by remember {
        mutableStateOf(false)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Spacer(modifier = Modifier.height(58.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
                isFirst = false
            },
            label = { Text(text = "Enter Your name") }
        )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mobile,
            onValueChange = {
                mobile = it
                isFirst = false
            },
            label = { Text(text = "Enter Your Mobile Number") }
        )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = address,
            onValueChange = { address = it },
            label = { Text(text = "Enter Your Address") }
        )

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = age,
            onValueChange = {
                age = it
                isFirst = false
            },
            label = { Text(text = "Enter Your Age") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )


        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = gender,
            onValueChange = {
                gender = it
                isFirst = false
            },
            label = { Text(text = "Enter Your Gender") }
        )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = birthDate,
            onValueChange = {
                birthDate = it
                isFirst = false
            },
            label = { Text(text = "Enter Your Birth-Date") }
        )

        Spacer(modifier = Modifier.height(5.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
                clickedAddPhoto = true
                isFirst = false
            }
        ) {
            Text(text = "Add Photo")
        }


        if (imageUri != null) {

            if (clickedAddPhoto) {
//                image.value = saveImageToInterStorage1(context, selectedImageUri)
                val uriToBitmap = uriToBitmap(imageUri!!, context)
                selectedImageUri = bitmapToByteArray(uriToBitmap!!)
                clickedAddPhoto = false
            }



            Log.d("URI", image.value)


//            if(image.value != "") {
//
//                var imageFile: File? = null
//
//                try {
//                    Log.d("TRY","INSIDE TRY")
//                    imageFile = File(context.filesDir, image.value)
//                } catch (e: FileNotFoundException) {
//
//                    Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
//                }
//
//                Log.d("FILE", imageFile.toString())
//                Image(
//                    painter = rememberAsyncImagePainter(imageFile),
//                    contentDescription = null
//                )
//            }

            Image(
                painter = rememberAsyncImagePainter(model = byteArrayToBitmap(selectedImageUri!!)),
                contentDescription = null
            )


//            AsyncImage(
//                model = selectedImageUri,
//                contentDescription = null,
//                modifier = Modifier.fillMaxWidth(),
//                contentScale = ContentScale.Fit
//            )
//            LoadImageFromUri(uri = selectedImageUri.toString(), context = context)

            Spacer(modifier = Modifier.height(5.dp))


//        Image(
//            painter = Painter()
//        )


//        if (clickedAddPhoto) {
//             image.value = saveImageToInterStorage(context, selectedImageUri)
//            Log.d("TAG1", image.value)
//            clickedAddPhoto = false
//        }




//        if (clickedAddPhoto and (selectedImageUri != null)) {
//            image = saveImageToInterStorage(context,selectedImageUri)
//            Log.d("TAG", image!!)
//            clickedAddPhoto = false
//        }
//
////            )
//
//        if (selectedImageUri != null){
//
//
//        Image(
//                painter = rememberAsyncImagePainter(File(context.filesDir, image)),
//                contentDescription = null
//            )
//            Log.d("IMAGEN", image!!)
//        //            AsyncImage(
////                model = selectedImageUri,
////                contentDescription = null,
////                modifier = Modifier.fillMaxWidth(),
////                contentScale = ContentScale.Crop
//
////            Log.d("URI",selectedImageUri.toString())
//
//            Spacer(modifier = Modifier.height(50.dp))
//        }

//        if (selectedImageUri != null) {
//            AsyncImage(
//                model = selectedImageUri,
//                contentDescription = null,
//                modifier = Modifier.fillMaxWidth(),
//                contentScale = ContentScale.Crop
//            )
//
////            Log.d("URI",selectedImageUri.toString())
//
//                Spacer(modifier = Modifier.height(5.dp))
//        }
        }



        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isUpdateButtonClicked = true
            },
            colors = ButtonDefaults.buttonColors(Color.Gray),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )
        ) {
            Text(text = upDateOrUploadButton,
            Modifier.padding(start = 5.dp)
            )
        }

        Spacer(modifier = Modifier.height(80.dp))
        if (isUpdateButtonClicked) {
            UpLoadUser(
                context = context,
                user = User(
                    id,
                    name,
                    mobile,
                    address,
                    gender,
                    age.toInt(),
                    birthDate,
                    selectedImageUri!!
                )
            )
            Log.d("TAG", "Upload User $selectedImageUri")
            isUpdateButtonClicked = false
        }

    }


    fun newImage() {
//        val newImage = ContentResolver.
    }

}


@Composable
fun UpLoadUser(context: Context, user: User) {

    val viewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(context, user)
    )

    val responseUser = viewModel.response.collectAsState().value

    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {

        viewModel.showErrorToastChannel.collectLatest { show ->
            if (!show) {
                Toast.makeText(
                    context, "Data uploaded Unsuccessful", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    if (responseUser) {
        Toast.makeText(
            context, "Data uploaded successful $responseUser", Toast.LENGTH_SHORT
        ).show()
    }
}

//@Composable
//fun LoadImageFromUri(uri: String , context: Context) {
//    val imageBitmap = remember { mutableStateOf<ImageBitmap?>(null) }
////    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
//
//    val loadBitmap: suspend () -> Unit = {
//        val inputStream: InputStream? = context.contentResolver.openInputStream(uri.toUri())
//        if (inputStream != null) {
//            val bitmap = BitmapFactory.decodeStream(inputStream)
//            val imageBitmapValue = withContext(Dispatchers.Default) {
//                bitmap.asImageBitmap()
//            }
//            imageBitmap.value = imageBitmapValue
//        }
//    }
//
//    DisposableEffect(uri) {
//        coroutineScope.launch {
//            loadBitmap()
//        }
//
//        onDispose {
//            // Cleanup
//            imageBitmap.value = null
//        }
//    }
//
//    imageBitmap.value?.let { Image(bitmap = it, contentDescription = "Loaded Image") }
//}


@Preview(showBackground = true)
@Composable
fun UserDetailPreview(){
    UserDetails()
}


fun saveImageToInterStorage1(context: Context, uri: Uri?): String {
    val fileName = "user_image.jpg"
    val file = File(context.filesDir, fileName)

    try {
        context.contentResolver.openInputStream(uri!!).use { inputStream ->
            FileOutputStream(file).use { outputStream ->
                inputStream!!.copyTo(outputStream)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
    }

    return file.absolutePath
}