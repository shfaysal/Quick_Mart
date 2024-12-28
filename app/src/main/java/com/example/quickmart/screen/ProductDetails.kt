package com.example.quickmart.screen

import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.quickmart.R
import com.example.quickmart.data.models.Product
import com.example.quickmart.data.models.Review
import com.example.quickmart.presentation.SingleProductViewModel
import com.example.quickmart.presentation.SingleProductViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okio.IOException
import kotlin.math.ceil
import kotlin.math.floor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails(id : String?){

//    val productJson = Json.decodeFromString<Product>(productJson)

    val viewModel : SingleProductViewModel = viewModel(
        factory = SingleProductViewModelFactory(id!!.toInt())
    )

    val response = viewModel.product.collectAsState().value
    val context = LocalContext.current

    var modalStateValue by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
        viewModel.showErrorToastChannel.collectLatest { show ->
            if (!show){
                Toast.makeText(context,"Something wrong is there", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(response.thumbnail)
            .size(Size.ORIGINAL)
            .build()
    ).state

    try {
        Log.d("RESPONSE", response.title)
    } catch (e: IOException){
        Log.d("RESPONSE", e.message.toString())
    }

    Log.d("PRODUCT ID","$id")

    val vouchers = listOf(
        Voucher(Color(0xFFADD8E6), spend = "$1500", discount = "$50"),
        Voucher(Color(0xFFFFC0CB), spend = "$2000", discount = "$75"),
        Voucher(Color(0xFFA4C639), spend = "$3000", discount = "$100"),
    )

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    val price = response.price/(1 - (response.discountPercentage/100))

//    LaunchedEffect(showBottomSheet) {
//        if (showBottomSheet){
//            scope.launch {
//                sheetState.show()
//            }
//        }else {
//            scope.launch {
//                sheetState.hide()
//            }
//        }
//    }




    Scaffold (
//        topBar = {
//            CenterAlignedTopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary
//                ),
//                title = {
//                Text(
//                    text = "Tap App Bar",
//                    textAlign = TextAlign.Center
//                )
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "back arrow"
//                        )
//                    }
//                }
//                )
//        }
    ) { paddingValues ->

        val backGroundColor = Color.White
        val background = Color(0xF5F5F5F5)


        Modifier.padding(paddingValues)
        val startPadding = 10.dp



        Box(
            modifier = Modifier
                .padding(top = 60.dp, bottom = 80.dp)
                .fillMaxSize()
                .background(background)
        ){

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 2.dp)
                    .align(Alignment.TopCenter)
                    .verticalScroll(rememberScrollState())
            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(300.dp),
//                    contentAlignment = Alignment.Center
//                ){
//                    if (imageState is AsyncImagePainter.State.Success){
//                        Image(
//                            modifier = Modifier
//                                .fillMaxSize(),
//                            painter = imageState.painter,
//                            contentDescription = null,
//                            contentScale = ContentScale.FillBounds
//                        )
//                    } else {
//                        CircularProgressIndicator()
//                    }
//                }

                ImageCarousel(images = response.images)


                Box(
                    modifier = Modifier
                        .background(backGroundColor)
                        .padding(5.dp)
                ){
                    Column {
                        Row (
                            modifier = Modifier
                                .padding(start = 10.dp, top = 10.dp)
                                .fillMaxWidth()
                                .background(backGroundColor),
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
                                            append("$${response.price}  ")
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.Black,
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 18.sp,
                                                textDecoration = TextDecoration.LineThrough
                                            )
                                        ){
                                            append(String.format("%.2f", price))
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
                                Text(text = "${response.discountPercentage}%")
                            }
                        }

                        Text(
                            modifier = Modifier.padding(start = startPadding, top = 2.dp, bottom = 2.dp),
                            text = response.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600
                        )
                        Text(
                            modifier = Modifier.padding(start = startPadding),
                            text = "Brand Name : ${response.brand}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600
                        )

                        Box (
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .align(Alignment.End)
                                .clickable {
                                    Log.d("TAG", "Click on share")
                                }
                        ){
                            Icon(
                                imageVector = Icons.Outlined.Share,
                                contentDescription = null,
                                tint = Color.Black.copy(alpha = .5f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .background(backGroundColor)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(background)
                    ) {
                        Column {
                            RowOfDetailsPage(R.drawable.returnp, response.returnPolicy,  onClick = {
                                scope.launch {
                                    sheetState.show()
                                }
                                modalStateValue = 0
                                showBottomSheet = true
                            })
                            RowOfDetailsPage(R.drawable.warranty, response.warrantyInformation, onClick = {
                                scope.launch {
                                    sheetState.show()
                                }
                                modalStateValue = 1
                                showBottomSheet = true
                            })
                            RowOfDetailsPage(R.drawable.shipping, response.shippingInformation, onClick = {
                                scope.launch {
                                    sheetState.show()
                                }
                                modalStateValue = 2
                                showBottomSheet = true
                            })
                            RowOfDetailsPage(R.drawable.availability, response.availabilityStatus, onClick = {
                                scope.launch {
                                    sheetState.show()
                                }
                                modalStateValue = 3
                                showBottomSheet = true
                            })

                        }
                    }
                }



                Spacer(modifier = Modifier.height(5.dp))

                Box (
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .background(backGroundColor)
                        .padding(10.dp)
                ) {
                    Column {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Vouchers",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = "Favourite",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                    fontFamily = FontFamily.SansSerif
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "arrow right icon",
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }


                        Row(
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 5.dp, end = 5.dp)
                                .horizontalScroll(rememberScrollState())
                        ) {

                            vouchers.forEachIndexed { index, voucher ->
                                VoucherCard(backGroundColor = voucher.backGroundColor, spend = voucher.spend, discount = voucher.discount)
                            }
                        }

//                        Spacer(modifier = Modifier.height(5.dp)
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .background(backGroundColor)
                        .fillMaxWidth()
                        .padding(10.dp)
                ){
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = ParagraphStyle(lineHeight = 20.sp)
                            ){
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.W600,
                                        fontSize = 16.sp
                                    )
                                ){
                                    append("Description\n")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                    )
                                ){
                                    append("${response.description}")
                                }
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backGroundColor)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Ratings & Reviews", fontWeight = FontWeight.Bold)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RatingBar(
                            stars = 5,
                            rating = response.rating
                        )
                        Text(text = "(${response.rating})")
//                        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
                    }
                }



                for (i in response.reviews){
                    CommentCard(review = i)
                }


                Spacer(modifier = Modifier.height(10.dp))

            }

            Box(
                modifier = Modifier
//                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(end = 20.dp, bottom = 5.dp)
                    .background(Color.Transparent)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.CenterEnd
            ){
                FloatingActionButton(
                    containerColor = Color(0xFF32CD32),
                    contentColor = Color.White,
                    onClick = {

                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(40.dp))

                }
//                Row(
////                    modifier = Modifier.background(Color.Gray),
//                    horizontalArrangement = Arrangement.spacedBy(20.dp),
//
//                ) {
//                    BuyAndAddButton("Buy Now")
//                    BuyAndAddButton("Add To Cart")
//                }

            }
        }
    }

    // Modal Bottom Sheet
    if (showBottomSheet){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                scope.launch {
                    sheetState.hide() // Dismiss the bottom sheet on request
                }
                showBottomSheet = false
            }
        ) {
            if (modalStateValue==0){

                CustomModalBottomSheetForReturn(
                    text = response.returnPolicy,
                    onClick = {
                        scope.launch {
                            sheetState.hide() // Dismiss the bottom sheet on request
                            showBottomSheet = false
                        }

                    })
            } else if(modalStateValue == 1){
                CustomModalBottomSheetForWarranty(text = response.warrantyInformation) {
                    scope.launch {
                        sheetState.hide() // Dismiss the bottom sheet on request
                        showBottomSheet = false
                    }
                }
            } else if( modalStateValue == 2){
                CustomModalBottomSheetForShipping(text = response.shippingInformation, text1 = response.minimumOrderQuantity.toString()) {
                    scope.launch {
                        sheetState.hide() // Dismiss the bottom sheet on request
                        showBottomSheet = false
                    }
                }
            }else if (modalStateValue == 3){
                CustomModalBottomSheetForStock(text = response.availabilityStatus, text1 = response.stock.toString()) {
                    scope.launch {
                        sheetState.hide() // Dismiss the bottom sheet on request
                        showBottomSheet = false
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(images : List<String>){

    val pagerState = rememberPagerState( pageCount = {
        images.size
    })

    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(Color(0xF5F5F5F5))
            .padding(10.dp)
    ){
        HorizontalPager(
            state = pagerState,
            beyondBoundsPageCount = 3,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) { page ->

            val imageState = rememberAsyncImagePainter(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(images[page])
                    .crossfade(true)
                    .size(Size.ORIGINAL)
                    .build()
            ).state


            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){

                when(imageState){
                    is AsyncImagePainter.State.Success -> {
                        Image(
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
        }

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.BottomCenter).padding(10.dp)
        ) {
            images.indices.forEach { index ->
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(10.dp)
                ){

                    val color = if ( index == pagerState.currentPage) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    }

                    Box(
                        modifier = Modifier
                            .size(5.dp)
//                            .padding(4.dp)
                            .background(color, shape = CircleShape)
                    )
                    
                }

            }

        }


    }


    
}



@Composable
fun BuyAndAddButton(text : String){
    TextButton(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .height(35.dp)
            .width(150.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green,
            contentColor = Color.White
        ),
        onClick = {

        }
    ) {
        Text(text = text, fontSize = 12.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = starsColor)
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.Gray
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

data class Voucher(
    val backGroundColor: Color,
    val spend: String,
    val discount : String
)

//@Preview
@Composable
fun VoucherCard( backGroundColor : Color, spend: String, discount : String){

    val modifier = Modifier
        .height(100.dp)
        .clip(RoundedCornerShape(5))
        .background(backGroundColor)

    Row (
        modifier = Modifier.padding(end = 5.dp)
    ) {
        Box (
            modifier = modifier.width(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                painter = painterResource(id = R.drawable.voucher),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

        }

        DotDivider(backGroundColor)

        Box (
            modifier = modifier
                .height(200.dp)
                .width(150.dp)
                .padding(start = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Text(
                    text = "Up to $discount off",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,

                )
                Text(text = "Min. Spend $spend",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,)
                Text(text = "Valid until 28 Dec 2024",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,)
                Box (
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(50))
                        .background(Color.Green)
                        .padding(vertical = 2.dp, horizontal = 5.dp)
                ) {
                    Text(text = "Copy Code", fontSize = 10.sp)
                }

            }
        }
    }
}

@Composable
fun DotDivider(color: Color) {
    Column(
        modifier = Modifier
            .height(100.dp)
            .padding(vertical = 5.dp)
            .background(color),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        repeat(10) { // Number of dots
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .width(.5.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(5))
            )
            Spacer(modifier = Modifier.height(2.dp)) // Space between dots
        }
    }
}



@Composable
fun RowOfDetailsPage(icon : Int, text : String, onClick: () -> Unit){
    Row(
        modifier = Modifier
            .padding(vertical = 2.dp, horizontal = 5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10))
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Row(
//            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Start Icon",
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // End Section: Icon
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "End Icon",
            modifier = Modifier.size(20.dp),
            tint = Color.Black
        )
    }
}


@Composable
fun CustomModalBottomSheetForReturn(text: String, onClick: () -> Unit){

    Box(modifier = Modifier
        .height(220.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
//        .background(Color.White)
        .padding(vertical = 20.dp, horizontal = 20.dp)
    ){

        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            ) {
                Text(
                    text = "Return Information",
                    modifier = Modifier.align(Alignment.Center)
                )
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { onClick() }
                )

            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = text,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600
                )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Change of mind is not applicable as a Return Reason. To learn more, please check Return Policy",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal
            )
        }

    }
}

@Composable
fun CustomModalBottomSheetForWarranty(text: String, onClick: () -> Unit){

    Box(modifier = Modifier
        .height(220.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
//        .background(Color.White)
        .padding(vertical = 20.dp, horizontal = 20.dp)
    ){

        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            ) {
                Text(
                    text = "Warranty Details",
                    modifier = Modifier.align(Alignment.Center)
                )
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { onClick() }
                )

            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = text,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = Modifier.height(10.dp))

        }

    }
}

@Composable
fun CustomModalBottomSheetForShipping(text: String, text1: String, onClick: () -> Unit){

    Box(modifier = Modifier
        .height(180.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
//        .background(Color.White)
        .padding(vertical = 20.dp, horizontal = 20.dp)
    ){

        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            ) {
                Text(
                    text = "Shipping Information",
                    modifier = Modifier.align(Alignment.Center)
                )
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { onClick() }
                )

            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = text,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Minimum order quantity : $text1",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal
            )
        }

    }
}

@Composable
fun CustomModalBottomSheetForStock(text: String, text1 : String, onClick: () -> Unit){

    Box(modifier = Modifier
        .height(180.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
//        .background(Color.White)
        .padding(vertical = 20.dp, horizontal = 20.dp)
    ){

        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            ) {
                Text(
                    text = "Stock Information",
                    modifier = Modifier.align(Alignment.Center)
                )
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { onClick() }
                )

            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = text,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Available Stock : $text1",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal
            )
        }

    }
}

@Composable
fun CommentCard(review: Review){
    Box (
        modifier = Modifier
//            .height(100.dp)
            .fillMaxWidth()
            .padding(bottom = 1.dp)
            .background(Color.White)
            .padding(10.dp)
    ) {
        Column (
//            modifier = Modifier.
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row (
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row{
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xF5F5F5F5)),
                        colorFilter = ColorFilter.tint(Color.Black.copy(alpha = .5f))
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = review.reviewerName, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(text = review.date, fontSize = 10.sp)
                    }
                }

                RatingBar(
                    rating = review.rating.toDouble(),
                    stars = 5
                )
            }

            Text(
                text = review.comment,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

    }
}

@Preview
@Composable
fun PreviewProductDetails(){
//    CommentCard()
//    ImageCarousel(images = listOf(
//        "https://cdn.dummyjson.com/products/images/fragrances/Gucci%20Bloom%20Eau%20de/1.png",
//        "https://cdn.dummyjson.com/products/images/fragrances/Gucci%20Bloom%20Eau%20de/2.png",
//        "https://cdn.dummyjson.com/products/images/fragrances/Gucci%20Bloom%20Eau%20de/3.png"
//    ))
    ProductDetails(id = "2")
//    ModalBottomSheet()
//    VoucherCard(backGroundColor = Color.Green, spend = "", discount = "")
}