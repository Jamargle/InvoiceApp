package com.jamarglex.invoiceapp.data.api.model
import kotlinx.serialization.Serializable

@Serializable
data class Invoice(
    val id: String,
    val title: String,
    val description: String
)
