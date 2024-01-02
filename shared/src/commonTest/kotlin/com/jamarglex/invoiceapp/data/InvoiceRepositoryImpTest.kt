package com.jamarglex.invoiceapp.data

import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.gen
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.element
import io.kotest.property.arbitrary.filterNot
import io.kotest.property.arbitrary.long
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class InvoiceRepositoryImpTest {

    private val repository = InvoiceRepositoryImp(
        allInvoices = fakeInvoices
    )

    @Test
    fun `getNewInvoice returns a new instance of Invoice with default values`() {
        val invoice = repository.getNewInvoice()

        invoice shouldBe Invoice(
            id = -123L,
            title = "",
            description = "",
            type = Invoice.Type.TEXT
        )
    }

    @Test
    fun `getAllInvoices returns list of invoices`() = runTest {
        val allInvoices = repository.getAllInvoices()

        allInvoices shouldBe listOf(
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
            )
        )
    }

    @Test
    fun `getInvoiceById returns null when given ID is not in the list of invoices available`() = runTest {
        val givenInvoiceId = Arb.long(min = 1).filterNot { it in fakeInvoices.map(Invoice::id) }.gen()
        val result = repository.getInvoiceById(givenInvoiceId)

        result shouldBe null
    }

    @Test
    fun `getInvoiceById returns the invoice by given ID when is within the list of invoices available`() = runTest {
        val givenInvoiceId = Arb.element(fakeInvoices.map(Invoice::id)).gen()
        val result = repository.getInvoiceById(givenInvoiceId)
        val expectedInvoice = fakeInvoices.first { it.id == givenInvoiceId }

        result shouldBe expectedInvoice
    }

}

private val fakeInvoices: List<Invoice> = listOf(
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
    )
)
