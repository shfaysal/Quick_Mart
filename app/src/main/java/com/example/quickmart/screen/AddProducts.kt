package com.example.quickmart.screen

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.quickmart.data.models.Product
import com.example.quickmart.presentation.PostProductViewModel
import com.example.quickmart.presentation.PostProductViewModelFactory
import com.example.quickmart.saveImageToInterStorage
import kotlinx.coroutines.flow.collectLatest
import java.io.File

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddProducts(){

//    val product = com.example.quickmart.data.models.Product(
//        "slkdfsf", "sldfkslfk", "sdsdfs", 10.9, 3,
//        listOf("sdfsdfs", "sdsf"), 23, 34.3, 3, "sfdsfd", "sdfsfd"
//    )


//    val viewModel : PostProductViewModel = viewModel(
//        factory = PostProductViewModelFactory(product)
//    )
//
//    val response = viewModel.product.collectAsState().value
//    val context = LocalContext.current
//
//    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
//
//        viewModel.showErrorToastChannel.collectLatest { show ->
//            if(!show){
//                Toast.makeText(
//                    context, "something wrong is there", Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//    }
//
//
//    if (response != null) {
//        Log.d("RESPONSE", response.title)
//    }



    var brandName by remember {
        mutableStateOf("")
    }

    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var discountPercentage by remember {
        mutableStateOf("2.3")
    }
    var id by remember { mutableStateOf("23") }
    var price by remember { mutableStateOf("34") }
    var rating by remember { mutableStateOf("4.5") }
    var stock by remember { mutableStateOf("") }
    var title by remember {
        mutableStateOf("")
    }
    var clickedAddPhoto by remember {
        mutableStateOf(false)
    }
    var image = remember {
        mutableStateOf("")
    }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    var uploadButton by remember {
        mutableStateOf(false)
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri})

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = {title = it},
            label = { Text(text = "Enter Your title")},
            textStyle = TextStyle.Default,
            shape = RoundedCornerShape(5.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = brandName,
            onValueChange = {brandName = it},
            label = { Text(text = "Brand Name")},
            textStyle = TextStyle.Default,
            shape = RoundedCornerShape(5.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = category,
            onValueChange = {category = it},
            label = { Text(text = "Category Name")},
            textStyle = TextStyle.Default,
            shape = RoundedCornerShape(5.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = {description = it},
            label = { Text(text = "Enter your description")},
            textStyle = TextStyle.Default,
            shape = RoundedCornerShape(5.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))



        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = price,
            onValueChange = {price = it},
            label = { Text(text = "Price")},
            textStyle = TextStyle.Default,
            shape = RoundedCornerShape(5.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = discountPercentage,
        onValueChange = {discountPercentage = it},
        label = { Text(text = "Discount Percentage")},
        textStyle = TextStyle.Default,
        shape = RoundedCornerShape(5.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = stock,
            onValueChange = {stock = it},
            label = { Text(text = "Enter your stock")},
            textStyle = TextStyle.Default,
            shape = RoundedCornerShape(5.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Spacer(modifier = Modifier.height(5.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
                clickedAddPhoto = true
//                isFirst = false
            }
        ) {
            Text(text = "Add Photo")
        }
        
        if (selectedImageUri != null) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
            
            Spacer(modifier = Modifier.height(5.dp))
        }

//        if (clickedAddPhoto and (selectedImageUri != null)) {
//            image.value = saveImageToInterStorage(context,selectedImageUri)
//            Log.d("IMAGE", image.value)
//
////            if (image.value != ""){
////                Image(
////                    painter = rememberAsyncImagePainter(File(context.filesDir, image.value)),
////                    contentDescription = null
////                )
////                Log.d("IMAGE1", image.value)
////
//            }
//
////            val imagePainter = rememberAsyncImagePainter(
////                model = ImageRequest.Builder(context)
////                    .data(selectedImageUri)
////                    .crossfade(true)
////                    .build(),
////            )
////
////            Image(painter = imagePainter,
////                contentDescription = null,
////                contentScale = ContentScale.Fit,
////                modifier = Modifier.padding(3.dp)
////            )
//
//            AsyncImage(
//                model = image.value,
//                contentDescription = null,
//                contentScale = ContentScale.Crop
//            )
//
//            Log.d("IMAGE1", image.value.toString())
//
//
//            Spacer(modifier = Modifier.height(100.dp))
//            clickedAddPhoto = false
//        }

//        if (selectedImageUri != null) {
//            val imagePainter = rememberAsyncImagePainter(
//                model = ImageRequest.Builder(context)
//                    .data(selectedImageUri)
//                    .crossfade(true)
//                    .build(),
//            )
//
//            Image(painter = imagePainter,
//                contentDescription = null,
//                contentScale = ContentScale.Fit,
//                modifier = Modifier.padding(3.dp)
//            )
//        }



        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                uploadButton = true
                }
        ) {
            Text(text = "Upload Button")
        }

        Spacer(modifier = Modifier.height(80.dp))


//        Spacer(modifier = Modifier.height(4.dp))
//
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = brandName,
//            onValueChange = {brandName = it},
//            label = { Text(text = "Brand Name")},
//            textStyle = TextStyle.Default,
//            shape = RoundedCornerShape(5.dp)
//        )
//        Spacer(modifier = Modifier.height(4.dp))
//
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = brandName,
//            onValueChange = {brandName = it},
//            label = { Text(text = "Brand Name")},
//            textStyle = TextStyle.Default,
//            shape = RoundedCornerShape(5.dp)
//        )
//        Spacer(modifier = Modifier.height(4.dp))

    }

    if (uploadButton){
        val response = PostProduct(Product.EMPTY)

        uploadButton = !uploadButton

        if (response != null) {
            Toast.makeText(
                LocalContext.current,
                "Successfully Updated",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


@Composable
fun PostProduct(product: Product): Product {

    val viewModel : PostProductViewModel = viewModel(
        factory = PostProductViewModelFactory(product)
    )

    val response = viewModel.product.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {

        viewModel.showErrorToastChannel.collectLatest { show ->
            if(!show){
                Toast.makeText(
                    context, "something wrong is there", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    return response
}
@Preview(showBackground = true)
@Composable
fun AddProductsReview(){
    AddProducts()
}