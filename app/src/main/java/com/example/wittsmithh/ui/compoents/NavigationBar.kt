package com.example.wittsmithh.ui.compoents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector


enum class NavigationBarItem(val label: String, val icon: ImageVector){
    Explore("Explore", Icons.Outlined.Home),
    Search("Search", Icons.Outlined.Search),
    Library("Library", Icons.Outlined.LibraryMusic)
}