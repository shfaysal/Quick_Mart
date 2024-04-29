package com.example.quickmart.drawer

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem (
    val title : String,
    val route : String,
    val selectIcon : ImageVector,
    val unselectIcon : ImageVector,
    var isItemClicked : Boolean = false,
)