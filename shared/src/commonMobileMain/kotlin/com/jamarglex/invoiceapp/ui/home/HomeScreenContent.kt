package com.jamarglex.invoiceapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
actual fun HomeScreenContent(
    padding: PaddingValues,
    uiState: HomeViewModel.UiState,
    onInvoiceClick: (Long) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(padding).fillMaxSize()
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        }

        InvoicesList(
            invoices = uiState.invoices,
            onInvoiceClick = { onInvoiceClick(it) }
        )
    }
}
