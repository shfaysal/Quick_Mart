package com.example.quickmart.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quickmart.data.models.Product
import com.example.quickmart.drawer.UserDetail
import kotlinx.serialization.json.Json
import kotlin.jvm.internal.Intrinsics.Kotlin

@Composable
fun Navigation(
     navController: NavHostController
) {
//    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    )
    {

        composable(route = Screen.Home.route){
            Home(navController)
        }

        composable(route = Screen.SearchScreen.route){
            SearchProducts()
        }

        composable(route = Screen.AddScreen.route){
            AddProducts()
        }

        composable(route = Screen.UserDetail.route){
            UserDetails()
        }

        composable(
            route = Screen.ProductDetails.route +"/{id}",
            arguments = listOf(
                navArgument("id"){type = NavType.StringType}
            )
        ){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
//            val product = productJson?.let { json ->
//                kotlinx.serialization.json.Json.decodeFromString<Product>(json)
//            }

//            val product = Json.decodeFromString<Product>(id)

            ProductDetails(id)

        }
    }
}