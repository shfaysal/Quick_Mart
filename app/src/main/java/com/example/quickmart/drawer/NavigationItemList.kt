package com.example.quickmart.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import com.example.quickmart.screen.Screen

object NavigationItemList {

    val drawerNavigationItemList : List<NavigationItem> = listOf(
        NavigationItem("Acc Information",Screen.UserDetail.route, Icons.Filled.AccountCircle,Icons.Outlined.AccountCircle),
        NavigationItem("Help and Feed Back","", Icons.Filled.Settings,Icons.Outlined.Settings),
        NavigationItem("Log Out","", Icons.Filled.Close,Icons.Outlined.Close)
    )

    val bottomBarItemList : List<NavigationItem> = listOf(
        NavigationItem("Home",Screen.Home.route,Icons.Filled.Home,Icons.Outlined.Home),
        NavigationItem("Search",Screen.SearchScreen.route,Icons.Filled.Search,Icons.Outlined.Search),
        NavigationItem("Add",Screen.AddScreen.route,Icons.Filled.Add,Icons.Outlined.Add)
    )
}