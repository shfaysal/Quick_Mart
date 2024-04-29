package com.example.quickmart.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quickmart.drawer.UserDetail

@Composable
fun Navigation(
     navController: NavHostController
) {
//    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route)
    {

        composable(route = Screen.Home.route){
            Home()
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


    }
}