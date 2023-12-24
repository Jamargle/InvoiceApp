package data

import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.domain.InvoiceRepository

object Repository : InvoiceRepository {

    override fun getAll(): List<Invoice> {
        return listOf(
            Invoice(
                title = "invoice 1",
                description = "The invoice 1",
                type = Invoice.Type.TEXT
            )
        )
    }

    override fun getById(invoiceId: Long): Invoice {
        return Invoice(
            id = invoiceId,
            title = "invoice 1",
            description = "The invoice 1",
            type = Invoice.Type.TEXT
        )
    }
}
