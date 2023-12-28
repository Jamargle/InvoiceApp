package com.jamarglex.invoiceapp.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.shared.Res

@Composable
actual fun DetailScreen(viewModel: DetailsViewModel, onClose: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(
                invoice = viewModel.state.invoice,
                onNavigationIconClicked = onClose
            )
        }
    ) { padding ->
        DetailsScreenContent(padding, viewModel.state)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    invoice: Invoice,
    onNavigationIconClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(invoice.title) },
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClicked
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = Res.string.back_button_content_description
                )
            }
        }
    )
}

@Composable
private fun DetailsScreenContent(
    padding: PaddingValues,
    uiState: DetailsViewModel.UiState
) {
    Box(modifier = Modifier.padding(padding)) {
        if (uiState.loading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier.padding(32.dp)
            ) {
                OutlinedTextField(
                    value = uiState.invoice.title,
                    readOnly = true,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Title")
                    },
                    maxLines = 1
                )

                OutlinedTextField(
                    value = uiState.invoice.description,
                    readOnly = true,
                    onValueChange = {},
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
