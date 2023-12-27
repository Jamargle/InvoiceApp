package com.jamarglex.invoiceapp.domain

interface InvoiceRepository {
    suspend fun getAllInvoices(): List<Invoice>
    suspend fun getInvoiceById(invoiceId: Long): Invoice?
}
