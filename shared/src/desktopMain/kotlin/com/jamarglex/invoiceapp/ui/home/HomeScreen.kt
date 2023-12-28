package com.jamarglex.invoiceapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.shared.Res

@Composable
actual fun HomeScreen(
    viewModel: HomeViewModel,
    onInvoiceClick: (Long) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onInvoiceClick(Invoice.NEW_INVOICE) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = Res.string.home_add_fab_content_description
                )
            }
        }
    ) { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(padding).fillMaxSize()
        ) {
            if (viewModel.state.isLoading) {
                CircularProgressIndicator()
            }

            InvoicesList(
                invoices = viewModel.state.invoices,
                onInvoiceClick = { onInvoiceClick(it) }
            )
        }
    }
}

@Composable
private fun InvoicesList(
    invoices: List<Invoice>,
    onInvoiceClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(invoices) { invoice ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(0.8f)
                    .clickable { onInvoiceClick(invoice.id) }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = invoice.title,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = invoice.description)
                }
            }
        }
    }
}
