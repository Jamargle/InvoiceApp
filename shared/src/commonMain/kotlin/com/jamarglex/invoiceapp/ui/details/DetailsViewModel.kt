package com.jamarglex.invoiceapp.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.domain.InvoiceRepository
import com.jamarglex.invoiceapp.shared.Res
import kotlinx.coroutines.launch

internal class DetailsViewModel(
    private val invoiceId: Long,
    private val invoiceRepository: InvoiceRepository
) : ScreenModel {

    var state by mutableStateOf<UiState>(UiState.Loading)
        private set

    init {
        if (invoiceId == Invoice.NEW_INVOICE_ID) {
            initStateForNewInvoice()
        } else {
            initStateForExistingInvoice()
        }
    }

    private fun initStateForNewInvoice() {
        val invoice = invoiceRepository.getNewInvoice()
        state = UiState.Editing(
            invoice = invoice,
            toolbarTitle = invoice.title
        )
    }

    private fun initStateForExistingInvoice() {
        screenModelScope.launch {
            val invoice = invoiceRepository.getInvoiceById(invoiceId)
            state = if (invoice != null) {
                UiState.Viewing(
                    invoice = invoice,
                    toolbarTitle = invoice.title
                )
            } else {
                val errorMessage = Res.string.invoice_details_error_initialising_view_invoice
                    .format(invoiceId = invoiceId.toString())
                UiState.Error(
                    errorMessage = errorMessage
                )
            }
        }
    }

    fun onEditInvoiceClicked() {
        screenModelScope.launch {
            val invoice = when (val uiState = state) {
                is UiState.Error -> invoiceRepository.getNewInvoice()
                is UiState.Viewing -> uiState.invoice
                is UiState.Editing -> uiState.invoice
                UiState.Loading -> null
            }

            if (invoice != null) {
                state = UiState.Editing(
                    invoice = invoice,
                    toolbarTitle = invoice.title
                )
            }
        }
    }

    fun onEditInvoiceTitleChange(newTitle: String) {
        (state as? UiState.Editing)?.let { currentState ->
            state = currentState.copy(
                invoice = currentState.invoice.copy(title = newTitle)
            )
        }
    }

    fun onEditInvoiceDescriptionChange(newDescription: String) {
        (state as? UiState.Editing)?.let { currentState ->
            state = currentState.copy(
                invoice = currentState.invoice.copy(description = newDescription)
            )
        }
    }

    fun onSaveInvoiceClicked() {
        // TODO
    }
}
