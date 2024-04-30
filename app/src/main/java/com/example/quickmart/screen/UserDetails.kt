package com.example.quickmart.screen

import android.annotation.SuppressLint
import android.content.Context
import android.service.autofill.UserData
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.quickmart.ImagePicker
import com.example.quickmart.data.database.UserDatabase
import com.example.quickmart.data.models.User
import com.example.quickmart.presentation.UserInfoGetViewModel
import com.example.quickmart.presentation.UserInfoGetViewModelFactory
import com.example.quickmart.presentation.UserViewModel
import com.example.quickmart.presentation.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent
import java.io.File

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun UserDetails(){

    val context = LocalContext.current


//    val user = User(0,"sazzad","mobile","dd","male",23,"sd","dsdf")

    val viewModel1 : UserInfoGetViewModel = viewModel(
        factory = UserInfoGetViewModelFactory(context)
    )

    val userdetails = viewModel1.userDetails.collectAsState().value

//    Log.d("TAG", userdetails!!.name)

//    if (userdetails != null) {
//        Log.d("TAG",userdetails.name)
//    }


    LaunchedEffect(key1 = viewModel1.showErrorTestChannel) {

        viewModel1.showErrorTestChannel.collectLatest {show ->
            if(!show) {
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



    val id = 1



    var isFirst by remember {
        mutableStateOf(true)
    }
    var name by remember { mutableStateOf("") }

    var mobile by remember { mutableStateOf("") }

    var address by remember { mutableStateOf("") }

    var gender by remember { mutableStateOf("") }

    var age by remember { mutableStateOf("") }

    var birthDate by remember { mutableStateOf("") }


    if(isFirst) {
        userdetails?.let {
            name = userdetails.name
            mobile = userdetails.mobile
            address = userdetails.address
            gender = userdetails.gender
            age = userdetails.age.toString()
            birthDate = userdetails.birthdate
        }
        Log.d("COUNT","$isFirst")
    }


    var image = remember {
        mutableStateOf("")
    }

    var clickedAddPhoto by remember {
        mutableStateOf(false)
    }

    var isUpdateButtonClicked by remember {
        mutableStateOf(false)
    }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)

    ) {
        Spacer(modifier = Modifier.height(58.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
                isFirst = false
                            },
            label = { Text(text = "Enter Your name")}
        )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mobile,
            onValueChange = {
                mobile = it
                isFirst = false
                            },
            label = { Text(text = "Enter Your Mobile Number")}
        )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = address,
            onValueChange = {address = it},
            label = { Text(text = "Enter Your Address")}
        )

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = age,
            onValueChange = {
                age = it
                isFirst = false
                            },
            label = { Text(text = "Enter Your Age")},
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
            label = { Text(text = "Enter Your Gender")}
        )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = birthDate,
            onValueChange = {
                birthDate = it
                isFirst = false
                            },
            label = { Text(text = "Enter Your Birth-Date")}
        )

        Spacer(modifier = Modifier.height(5.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                clickedAddPhoto = true
            }
        ) {
            Text(text = "Add Photo")
        }

        if (clickedAddPhoto) {
            image.value = ImagePicker()
            Log.d("TAG1", image.value)
            clickedAddPhoto = false
        }

        if(image.value != "") {
            Image(painter = rememberAsyncImagePainter(File(context.filesDir,image.value)),
                contentDescription = null)
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isUpdateButtonClicked = true
            }
        ) {
            Text(text = "Update")
        }
    }

    if (isUpdateButtonClicked) {
        UpLoadUser(context = context, user = User(userdetails!!.id,name,mobile,address,gender,age.toInt(),birthDate,"no photo"))
        Log.d("TAG","Upload User")
        isUpdateButtonClicked = false
    }

}

@Composable
fun UpLoadUser(context: Context, user: User) {

    val viewModel : UserViewModel = viewModel(
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
            context, "Data uploaded successful", Toast.LENGTH_SHORT
        ).show()
    }
}





@Preview(showBackground = true)
@Composable
fun UserDetailPreview(){
    UserDetails()
}