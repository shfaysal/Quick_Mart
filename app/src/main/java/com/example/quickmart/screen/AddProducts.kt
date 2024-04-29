package com.example.quickmart.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import android.widget.Toast
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quickmart.presentation.PostProductViewModel
import com.example.quickmart.presentation.PostProductViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddProducts(){

    val product = com.example.quickmart.data.models.Product(
        "slkdfsf", "sldfkslfk", "sdsdfs", 10.9, 3,
        listOf("sdfsdfs", "sdsf"), 23, 34.3, 3, "sfdsfd", "sdfsfd"
    )


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

    if (response != null) {
        Log.d("RESPONSE", response.title)
    }



    var brandName by mutableStateOf("")
    var category by mutableStateOf("")
    var description by mutableStateOf("")
    var discountPercentage by mutableStateOf("")
    var id by mutableStateOf("")
    var price by mutableStateOf("")
    var rating by mutableStateOf("")
    var stock by mutableStateOf("")
    var title by mutableStateOf("")


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
            .background(Color.Green)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

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
            value = id,
            onValueChange = {id = it},
            label = { Text(text = "Brand Name")},
            textStyle = TextStyle.Default,
            shape = RoundedCornerShape(5.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = price,
            onValueChange = {price = it},
            label = { Text(text = "Brand Name")},
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

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = {title = it},
            label = { Text(text = "Enter Your title")},
            textStyle = TextStyle.Default,
            shape = RoundedCornerShape(5.dp)
        )
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
}

@Preview(showBackground = true)
@Composable
fun AddProductsReview(){
    AddProducts()
}