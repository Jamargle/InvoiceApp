package com.jamarglex.invoiceapp.domain

import kotlinx.serialization.Serializable

@Serializable
data class Invoice(
    val id: Long = NEW_INVOICE,
    val title: String,
    val description: String,
    val type: Type
) {
    companion object {
        const val NEW_INVOICE = -1L
    }
    enum class Type { TEXT, AUDIO }
}
