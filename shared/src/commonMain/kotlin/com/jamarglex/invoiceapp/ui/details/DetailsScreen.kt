package com.jamarglex.invoiceapp.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.core.parameter.parametersOf

data class DetailScreen(private val invoiceId: Long) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val detailsViewModel = getScreenModel<DetailsViewModel> { parametersOf(invoiceId) }
        DetailScreen(
            viewModel = detailsViewModel,
            onClose = { navigator.pop() }
        )
    }

}

@Composable
private fun DetailScreen(viewModel: DetailsViewModel, onClose: () -> Unit) {
    Scaffold(
        topBar = {
            DetailScreenTopBar(
                invoice = viewModel.state.invoice,
                onNavigationIconClicked = onClose
            )
        },
        content = { padding ->
            DetailsScreenContent(padding, viewModel.state)
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
