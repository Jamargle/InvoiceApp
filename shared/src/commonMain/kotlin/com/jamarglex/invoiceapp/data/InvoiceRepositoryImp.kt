package com.jamarglex.invoiceapp.data

import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.domain.InvoiceRepository

internal object InvoiceRepositoryImp : InvoiceRepository {

    override fun getAll(): List<Invoice> {
        return allInvoices
    }

    override fun getById(invoiceId: Long): Invoice? {
        return allInvoices.firstOrNull { it.id == invoiceId }
    }

    private val allInvoices = listOf(
        Invoice(
            title = "invoice 1",
            description = "The invoice 1",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            title = "invoice 2",
            description = "The invoice 2",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            title = "invoice 3",
            description = "The invoice 3",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            title = "invoice 4",
            description = "The invoice4",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            title = "invoice 5",
            description = "The invoice 5",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            title = "invoice 6",
            description = "The invoice 6",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            title = "invoice 7",
            description = "The invoice 7",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            title = "invoice 8",
            description = "The invoice 8",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            title = "invoice 9",
            description = "The invoice 9",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            title = "invoice 10",
            description = "The invoice 10",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            title = "invoice 11",
            description = "The invoice 11",
            type = Invoice.Type.TEXT
        )
    )
}
