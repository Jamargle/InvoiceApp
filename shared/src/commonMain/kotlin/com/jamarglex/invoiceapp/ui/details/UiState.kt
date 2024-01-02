package com.jamarglex.invoiceapp.ui.details

import com.jamarglex.invoiceapp.domain.Invoice

internal interface WithToolbar {
    val toolbarTitle: String
}

internal sealed interface UiState {
    data object Loading : UiState

    data class Viewing(
        val invoice: Invoice,
        override val toolbarTitle: String
    ) : UiState, WithToolbar

    data class Editing(
        val invoice: Invoice,
        override val toolbarTitle: String
    ) : UiState, WithToolbar

    data class Error(
        val errorMessage: String
    ) : UiState
}
