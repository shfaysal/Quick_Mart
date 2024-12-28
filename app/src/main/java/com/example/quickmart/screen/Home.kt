package com.example.quickmart.screen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.movableContentWithReceiverOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.quickmart.data.models.Product
import com.example.quickmart.presentation.ProductViewModel
import com.example.quickmart.presentation.ProductViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun Home(navController: NavController){

    val backGroundColor = Color.White
    val background = Color(0xF5F5F5F5)

    val viewModel : ProductViewModel = viewModel(
        factory = ProductViewModelFactory()
    )

    val productList = viewModel.products.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.showErrorTestChannel) {

        viewModel.showErrorTestChannel.collectLatest { show ->
            if(!show){
                Toast.makeText(
                    context, "something wrong is there", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    if(productList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(vertical = 55.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp)
        ) {

            items(productList.size){index ->
                Product(productList[index], navController)
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun Product( product : Product, navController: NavController){



    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(product.thumbnail)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Column (
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
//            .height(300.dp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 10.dp)
            .clickable {
                try {
//                    val productJson = Uri.encode(Json.encodeToString(product))
//                    Log.d("CLICKED", "Clicked on ${product.title}")
                    navController.navigate(Screen.ProductDetails.withArgs(product.id.toString()))
                } catch (e: Exception) {
                    Log.d("JSON ERROR", e.message.toString())
                }
            }
    ) {

//        if (imageStage is AsyncImagePainter.State.Error) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(180.dp),
//                contentAlignment = Alignment.Center
//            ){
//                CircularProgressIndicator()
//            }
//        }

//        if (imageStage is AsyncImagePainter.State.Success) {
//            Image(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp),
//                painter = imageStage.painter,
//                contentDescription = product.title,
//                contentScale = ContentScale.Crop
//            )
//        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ){
            when(imageState){
                is AsyncImagePainter.State.Success -> {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = imageState.painter,
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
                else -> {
                    CircularProgressIndicator()
                }
            }

        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "${product.title}",
            fontSize =  17.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start
        )

        Text(
            text = "Brand : ${product.brand}",
            fontSize =  17.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start
        )

        productPrice(price = product.price, discountPercentage = product.discountPercentage)
    }
}

@Composable
fun productPrice(price : Double, discountPercentage: Double){

    val actualPrice = price/(1 - (discountPercentage/100))

    Row (
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = ParagraphStyle(lineHeight = 20.sp)
                ){
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                    ){
                        append("$$price  ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                    ){
                        append(String.format("%.2f", actualPrice))
                    }
                }
            }
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box (
            modifier = Modifier
                .padding(5.dp)
                .clip(shape = RoundedCornerShape(50))
                .background(Color.Green)
        ) {
            Text(text = "$discountPercentage%")
        }
    }
}