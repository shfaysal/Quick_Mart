package com.example.quickmart.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quickmart.presentation.ProductViewModelFactory
import com.example.quickmart.presentation.SearchProductViewModel
import com.example.quickmart.presentation.SearchViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchProducts(){

    var text by remember {
        mutableStateOf("iphone")
    }

    var viewModel : SearchProductViewModel = viewModel(
        factory = SearchViewModelFactory(text)
    )

    Scaffold(
        topBar = {
            SearchBarHandle(
                onSearchTextChange = {searchText ->
//                    viewModel = viewModel(
//                        factory = ProductViewModelFactory(searchText)
//                    )

                    text = searchText

                }
            )
        },
        content = { paddingValues ->
            val value = paddingValues
            SearchProducts(viewModel)
        }
    )

}

@Composable
fun SearchProducts(viewModel: SearchProductViewModel){


    val searchProductList = viewModel.products.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {

        viewModel.showErrorToastChannel.collectLatest { show ->
            if(!show){
                Toast.makeText(
                    context, "something is wrong there", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    if (searchProductList.isEmpty()){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else {
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp)
        ) {
            items(searchProductList.size) {index ->
                Product(searchProductList[index])
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarHandle(onSearchTextChange :   (String) -> Unit) {

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        query = text,
        onQueryChange = {
            text = it
            onSearchTextChange(text)
        },
        onSearch = {
              active = false
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
             Text(text = "Write here")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        if (text.isEmpty()) {
                            text = ""
                        } else {
                            active = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                )
            }
        }
    ) {}
}