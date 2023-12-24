package com.jamarglex.invoiceapp.domain

interface InvoiceRepository {
    fun getAll(): List<Invoice>
    fun getById(invoiceId: Long): Invoice
}
