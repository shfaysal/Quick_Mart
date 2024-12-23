package com.example.quickmart.drawer

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Person
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.quickmart.byteArrayToBitmap
import com.example.quickmart.presentation.UserInfoGetViewModel
import com.example.quickmart.presentation.UserInfoGetViewModelFactory
import kotlinx.coroutines.flow.collectLatest


@Composable
fun DrawerHeader(){

    val context = LocalContext.current

    val viewModel: UserInfoGetViewModel = viewModel(
        factory = UserInfoGetViewModelFactory(context)
    )

    val user = viewModel.userDetails.collectAsState().value

    LaunchedEffect (key1 = viewModel.showErrorTestChannel) {
        viewModel.showErrorTestChannel.collectLatest { show ->
            if (!show) {
                Toast.makeText(
                    context, "Data Loaded Unsuccessful", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Log.d("DRAW","DrawHeader")

    var image by remember {
        mutableStateOf<ByteArray?>(null)
    }

    var retrieveImage by remember {
        mutableStateOf<Bitmap?>(null)
    }

    var text by remember {
        mutableStateOf("No name")
    }

    user?.let {
        image = user.photo
        text = user.name
    }

//    retrieveImage = byteArrayToBitmap(image!!)


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

            if (image != null) {
                retrieveImage = byteArrayToBitmap(image!!)
                AsyncImage(
                    model = retrieveImage,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(Color.Green),
                    alignment = Alignment.Center,
                )
            } else {
                Image(
                    imageVector = Icons.Default.Person,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(Color.Green),
                    alignment = Alignment.Center,
                )
            }


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


//@Preview(showBackground = true )
//@Composable
//fun DrawerHeaderPreview(){
//    DrawerHeader(user )
//}