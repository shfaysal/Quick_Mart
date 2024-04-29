package com.example.quickmart.screen

sealed class Screen (val route: String){
    data object Home: Screen("home_screen")
    data object SearchScreen: Screen("search_screen")
    data object AddScreen: Screen("add_screen")
    data object UserDetail: Screen("userDetail_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {args ->
                append("/$args")
            }
        }
    }
}