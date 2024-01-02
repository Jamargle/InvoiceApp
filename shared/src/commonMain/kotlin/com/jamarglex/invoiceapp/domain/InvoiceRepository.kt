package com.jamarglex.invoiceapp.domain

interface InvoiceRepository {
    fun getNewInvoice(): Invoice
    suspend fun getAllInvoices(): List<Invoice>
    suspend fun getInvoiceById(invoiceId: Long): Invoice?
}
