package com.example.quickmart.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
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
import com.example.quickmart.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails(){

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                Text(
                    text = "Tap App Bar",
                    textAlign = TextAlign.Center
                )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back arrow"
                        )
                    }
                }
                )
        }
    ) { paddingValues ->

        val backGroundColor = Color.White
        val background = Color(0xF5F5F5F5)


        Modifier.padding(paddingValues)
        val startPadding = 10.dp

        Box(
            modifier = Modifier
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
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    painter = painterResource(id = R.drawable.sazzadlinkedin1),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )

                Box(
                    modifier = Modifier
                        .background(backGroundColor)
                        .padding(5.dp)
                ){
                    Column {
                        Row (
                            modifier = Modifier
//                        .height(100.dp)
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
                                            append("$50  ")
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.Black,
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 18.sp,
                                                textDecoration = TextDecoration.LineThrough
                                            )
                                        ){
                                            append("$60")
                                        }
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Box (
                                modifier = Modifier
//                            .height(20.dp)
                                    .padding(5.dp)
                                    .clip(shape = RoundedCornerShape(50))
                                    .background(Color.Green)
                            ) {
                                Text(text = "-15.7%")
                            }
                        }

                        Text(
                            modifier = Modifier.padding(startPadding),
                            text = "Miss & Mrs Nail Polish For Women Shade - 100",
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
                            RowOfDetailsPage(onClick = {

                            })
                            RowOfDetailsPage(onClick = {

                            })
                            RowOfDetailsPage(onClick = {

                            })
                            RowOfDetailsPage(onClick = {

                            })

                        }
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                Box (
                    modifier = Modifier
                        .height(200.dp)
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

                        VoucherCard(backGroundColor = Color(0xFFADD8E6), spend = "", discount = "")
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))

            }

            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color.Green)
                    .align(Alignment.BottomCenter)
            ){
                Text(text = "Bottom")
            }

        }





    }
}

//@Preview
@Composable
fun VoucherCard( backGroundColor : Color, spend: String, discount : String){

    val modifier = Modifier
        .height(80.dp)
        .clip(RoundedCornerShape(10))
        .background(backGroundColor)



    Row {
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

        DotDivider()

        Box (
            modifier = modifier
                .height(200.dp)
                .width(200.dp)
                .padding(start = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Text(
                    text = "Up to $50 off",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,

                )
                Text(text = "Min. Spend $1500",
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
fun DotDivider() {
    Column(
        modifier = Modifier
            .height(80.dp)
            .padding(vertical = 5.dp)
            .background(Color(0xFFADD8E6)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        repeat(8) { // Number of dots
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
fun RowOfDetailsPage(onClick: () -> Unit){
    Row(
        modifier = Modifier
            .padding(vertical = 2.dp, horizontal = 5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10))
            .clickable {
                onClick
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Row(
//            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Start Icon",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
            Text(
                text = "7 days easy return",
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




@Preview
@Composable
fun PreviewProductDetails(){
    ProductDetails()
//    VoucherCard(backGroundColor = Color.Green, spend = "", discount = "")
}