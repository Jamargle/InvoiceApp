package com.jamarglex.invoiceapp.data

import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.domain.InvoiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal object InvoiceRepositoryImp : InvoiceRepository {

    override suspend fun getAllInvoices(): List<Invoice> {
        return withContext(Dispatchers.IO) {
            allInvoices
        }
    }

    override suspend fun getInvoiceById(invoiceId: Long): Invoice? {
        return withContext(Dispatchers.IO) {
            allInvoices.firstOrNull { it.id == invoiceId }
        }
    }

    private val allInvoices = listOf(
        Invoice(
            id = 1,
            title = "invoice 1",
            description = "The invoice 1",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            id = 2,
            title = "invoice 2",
            description = "The invoice 2",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            id = 3,
            title = "invoice 3",
            description = "The invoice 3",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            id = 4,
            title = "invoice 4",
            description = "The invoice4",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            id = 5,
            title = "invoice 5",
            description = "The invoice 5",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            id = 6,
            title = "invoice 6",
            description = "The invoice 6",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            id = 7,
            title = "invoice 7",
            description = "The invoice 7",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            id = 8,
            title = "invoice 8",
            description = "The invoice 8",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            id = 9,
            title = "invoice 9",
            description = "The invoice 9",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            id = 10,
            title = "invoice 10",
            description = "The invoice 10",
            type = Invoice.Type.TEXT
        ),
        Invoice(
            id = 11,
            title = "invoice 11",
            description = "The invoice 11",
            type = Invoice.Type.TEXT
        )
    )
}
