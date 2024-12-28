package com.example.quickmart.screen

import android.net.Uri

sealed class Screen (val route: String){
    data object Home: Screen("home_screen")
    data object SearchScreen: Screen("search_screen")
    data object AddScreen: Screen("add_screen")
    data object UserDetail: Screen("userDetail_screen")
    data object ProductDetails : Screen("product_details")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {args ->
                append("/$args")
            }
        }
    }
}