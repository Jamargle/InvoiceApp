package com.jamarglex.invoiceapp.domain

import kotlinx.serialization.Serializable

@Serializable
data class Invoice(
    val id: Long,
    val title: String,
    val description: String,
    val type: Type
) {
    companion object {
        const val NEW_INVOICE_ID = -123L
    }

    enum class Type { TEXT, AUDIO }
}
