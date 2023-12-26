package com.jamarglex.invoiceapp

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.jamarglex.invoiceapp.ui.home.HomeScreen

@Composable
fun App() {
    Navigator(
        screen = HomeScreen
    )
}
