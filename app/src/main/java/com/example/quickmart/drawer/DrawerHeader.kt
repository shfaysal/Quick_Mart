package com.example.quickmart.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DrawerHeader(){

    var image by remember {
        mutableStateOf(Icons.Rounded.Person)
    }

    var text by remember {
        mutableStateOf("No name")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//            Image(
//                painter = Icons.Rounded.Person,
//                contentDescription = )

            Image(
                imageVector = image,
                contentScale = ContentScale.Crop,
                contentDescription = "Image",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Color.Green),
                alignment = Alignment.Center,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = text,
                fontSize = 30.sp,
                fontStyle = FontStyle.Normal,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview(showBackground = true )
@Composable
fun DrawerHeaderPreview(){
    DrawerHeader()
}