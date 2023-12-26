package com.jamarglex.invoiceapp.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jamarglex.invoiceapp.getPlatform

@Composable
actual fun HomeScreen(viewModel: HomeViewModel, onInvoiceClick: (Long) -> Unit) {
    Text("This is Home screen in desktop: ${getPlatform()}")
}
