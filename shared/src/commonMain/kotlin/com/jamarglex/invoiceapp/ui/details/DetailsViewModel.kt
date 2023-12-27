package com.jamarglex.invoiceapp.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.domain.InvoiceRepository
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val invoiceId: Long,
    private val invoiceRepository: InvoiceRepository
) : ScreenModel {

    var state by mutableStateOf(UiState())
        private set

    init {
        if (invoiceId != Invoice.NEW_INVOICE) {
            loadInvoice()
        }
    }

    private fun loadInvoice() {
        screenModelScope.launch {
            state = UiState(loading = true)
            val invoice = invoiceRepository.getInvoiceById(invoiceId)
            if (invoice != null) {
                state = UiState(invoice = invoice)
            }
            // TODO error handling...
        }
    }

    data class UiState(
        val invoice: Invoice = Invoice(title = "", description = "", type = Invoice.Type.TEXT),
        val loading: Boolean = false
    )
}
