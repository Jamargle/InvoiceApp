package com.jamarglex.invoiceapp.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jamarglex.invoiceapp.shared.Res
import org.koin.core.parameter.parametersOf

data class DetailScreen(private val invoiceId: Long) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val detailsViewModel = getScreenModel<DetailsViewModel> { parametersOf(invoiceId) }
        DetailScreen(
            viewModel = detailsViewModel,
            onClose = { navigator.pop() },
            onEditInvoiceClicked = detailsViewModel::onEditInvoiceClicked,
            onEditInvoiceTitleChange = detailsViewModel::onEditInvoiceTitleChange,
            onEditInvoiceDescriptionChange = detailsViewModel::onEditInvoiceDescriptionChange,
            onSaveInvoiceClicked = detailsViewModel::onSaveInvoiceClicked
        )
    }

}

@Composable
private fun DetailScreen(
    viewModel: DetailsViewModel,
    onClose: () -> Unit,
    onEditInvoiceClicked: () -> Unit,
    onEditInvoiceTitleChange: (String) -> Unit,
    onEditInvoiceDescriptionChange: (String) -> Unit,
    onSaveInvoiceClicked: () -> Unit
) {
    val uiState = viewModel.state
    Scaffold(
        topBar = {
            if (uiState is WithToolbar) {
                DetailScreenTopBar(
                    toolbarText = uiState.toolbarTitle,
                    onNavigationIconClicked = onClose
                )
            }
        },
        content = { padding ->
            DetailsScreenContent(
                padding,
                viewModel.state,
                onEditInvoiceClicked,
                onEditInvoiceTitleChange,
                onEditInvoiceDescriptionChange,
                onSaveInvoiceClicked
            )
        }
    )
}

@Composable
private fun DetailsScreenContent(
    padding: PaddingValues,
    uiState: UiState,
    onEditInvoiceClicked: () -> Unit,
    onEditInvoiceTitleChange: (String) -> Unit,
    onEditInvoiceDescriptionChange: (String) -> Unit,
    onSaveInvoiceClicked: () -> Unit
) {
    Box(modifier = Modifier.padding(padding)) {
        when (uiState) {
            UiState.Loading -> CircularProgressIndicator()
            is UiState.Error -> InvoiceDetailsError(uiState)
            is UiState.Editing -> EditInvoiceScreen(
                uiState,
                onEditInvoiceTitleChange,
                onEditInvoiceDescriptionChange,
                onSaveInvoiceClicked
            )

            is UiState.Viewing -> ViewInvoiceScreen(
                uiState,
                onEditInvoiceClicked
            )
        }
    }
}

@Composable
private fun EditInvoiceScreen(
    uiState: UiState.Editing,
    onEditInvoiceTitleChange: (String) -> Unit,
    onEditInvoiceDescriptionChange: (String) -> Unit,
    onSaveInvoiceIconClicked: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(32.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = uiState.invoice.title,
                onValueChange = onEditInvoiceTitleChange,
                label = { Text(text = Res.string.invoice_details_title_label) },
                maxLines = 1
            )

            OutlinedTextField(
                value = uiState.invoice.description,
                onValueChange = onEditInvoiceDescriptionChange,
                label = { Text(Res.string.invoice_details_description_label) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            onClick = onSaveInvoiceIconClicked
        ) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = Res.string.done_save_invoice_fab_content_description
            )
        }
    }
}

@Composable
private fun ViewInvoiceScreen(
    uiState: UiState.Viewing,
    onEditInvoiceIconClicked: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(32.dp)
        ) {
            OutlinedTextField(
                value = uiState.invoice.title,
                readOnly = true,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = Res.string.invoice_details_title_label) },
                maxLines = 1
            )

            OutlinedTextField(
                value = uiState.invoice.description,
                readOnly = true,
                onValueChange = {},
                label = { Text(Res.string.invoice_details_description_label) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            onClick = onEditInvoiceIconClicked
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = Res.string.edit_menu_content_description
            )
        }
    }
}

@Composable
private fun InvoiceDetailsError(
    uiState: UiState.Error
) {
    Column(
        modifier = Modifier.padding(48.dp)
    ) {
        Text(
            text = uiState.errorMessage
        )
    }
}
