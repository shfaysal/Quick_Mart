package com.example.quickmart

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quickmart.data.api.RetrofitInstance
import com.example.quickmart.data.repositories.ProductRepositoryImplementation
import com.example.quickmart.drawer.AppBar
import com.example.quickmart.drawer.BottomBarBody
import com.example.quickmart.drawer.DrawerBody
import com.example.quickmart.drawer.DrawerHeader
import com.example.quickmart.drawer.NavigationItemList
import com.example.quickmart.presentation.ProductViewModel
import com.example.quickmart.presentation.ProductViewModelFactory
import com.example.quickmart.presentation.UserInfoGetViewModel
import com.example.quickmart.presentation.UserInfoGetViewModelFactory
import com.example.quickmart.screen.AddProducts
import com.example.quickmart.screen.Home
import com.example.quickmart.screen.Navigation
import com.example.quickmart.screen.Screen
import com.example.quickmart.screen.SearchProducts
import com.example.quickmart.ui.theme.QuickMartTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



class MainActivity : ComponentActivity() {

//    private val viewModel by viewModels<ProductViewModel>(factoryProducer = {
//        object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return ProductViewModel(ProductRepositoryImplementation(RetrofitInstance.api))
//                as T
//            }
//        }
//    })

//    private val viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickMartTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    val navController = rememberNavController()

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(.7f)
                                    .fillMaxHeight()
                                    .background(Color.White)
                            ) {
                                DrawerHeader()
                                Divider(
                                    modifier = Modifier.fillMaxWidth(),
                                    thickness = 3.dp,
                                    color = Color.Green
                                )
                                DrawerBody(
                                    items = NavigationItemList.drawerNavigationItemList,
                                    onItemClick = {
                                        Log.d("TAG","${it.route} is clicked")
                                        scope.launch {
                                            drawerState.close()
                                        }
                                        navController.navigate(it.route)
                                    }
                                )
                            }
                        },
                    ) {

                        Scaffold (
                            topBar = {
                                AppBar (
                                    onNavigationIconClick = {
                                        scope.launch {
                                            drawerState.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }
                                    }
                                )
                            },
                            bottomBar = {
                                BottomBarBody(
                                    items = NavigationItemList.bottomBarItemList,
                                    onItemClick = {item ->
                                        Log.d("TAG","${item.title} is clicked")
                                        navController.navigate(item.route)
                                    }
                                )
                            }
                        ) { paddingValues ->

                            val value = paddingValues

                            Navigation(navController)

//                            NavHost(
//                                navController = navController,
//                                startDestination = Screen.Home.route)
//                            {
//
//                                composable(route = Screen.Home.route){
//                                    Home()
//                                }
//
//                                composable(route = Screen.SearchScreen.route){
//                                    SearchProducts()
//                                }
//
//                                composable(route = Screen.AddScreen.route){
//                                    AddProducts()
//                                }
//                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuickMartTheme {
        Greeting("Android")
    }
}