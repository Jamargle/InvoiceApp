package com.jamarglex.invoiceapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.domain.InvoiceRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val invoiceRepository: InvoiceRepository
) : ScreenModel {

    var state by mutableStateOf(UiState(invoices = emptyList(), isLoading = false))
        private set

    init {
        loadInvoices()
    }

    private fun loadInvoices() {
        screenModelScope.launch {
            state = state.copy(isLoading = true)
            val response = invoiceRepository.getAllInvoices()
            state = state.copy(invoices = response, isLoading = false)
        }
    }

    data class UiState(
        val invoices: List<Invoice>,
        val isLoading: Boolean
    )
}
