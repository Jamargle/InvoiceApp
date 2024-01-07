package com.jamarglex.invoiceapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.domain.InvoiceRepository
import com.jamarglex.invoiceapp.domain.SessionRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val sessionRepository: SessionRepository,
    private val invoiceRepository: InvoiceRepository
) : ScreenModel {

    private val navigationChannel by lazy { Channel<NavigationEvent>(Channel.BUFFERED) }
    val navigationEvents: Flow<NavigationEvent> by lazy { navigationChannel.receiveAsFlow() }

    var state by mutableStateOf(UiState(invoices = emptyList(), isLoading = false))
        private set

    init {
        screenModelScope.launch {
            val isUserLoggedIn = sessionRepository.isUserLoggedIn()
            if (isUserLoggedIn) {
                loadInvoices()
            } else {
//                navigationChannel.send(NavigationEvent.ToLogin)
            }
        }
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

    sealed interface NavigationEvent {
        data object ToLogin : NavigationEvent
    }
}
