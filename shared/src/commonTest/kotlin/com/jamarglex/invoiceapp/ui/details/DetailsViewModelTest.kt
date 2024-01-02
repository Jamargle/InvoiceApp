package com.jamarglex.invoiceapp.ui.details

import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.domain.Invoice.Companion.NEW_INVOICE_ID
import com.jamarglex.invoiceapp.domain.InvoiceRepository
import com.jamarglex.invoiceapp.gen
import com.jamarglex.invoiceapp.genList
import com.jamarglex.invoiceapp.genOrNull
import com.jamarglex.invoiceapp.invoice
import com.jamarglex.invoiceapp.shared.Res
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.filterNot
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.string
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `sets uiState as Editing when viewModel is created with an invoiceId NEW_INVOICE_ID`() = runTest {
        val newInvoice = Arb.invoice().gen()
        val invoiceRepository = getFakeInvoiceRepository(
            withGetNewInvoice = newInvoice
        )

        val aViewModel = DetailsViewModel(
            invoiceId = NEW_INVOICE_ID,
            invoiceRepository = invoiceRepository
        )
        aViewModel.state shouldBe UiState.Editing(
            invoice = newInvoice,
            toolbarTitle = newInvoice.title
        )
    }

    @Test
    fun `sets uiState as Viewing when viewModel is created with an invoiceId different than NEW_INVOICE_ID that exists`() = runTest {
        val existingInvoice = Arb.invoice().gen()
        val invoiceRepository = getFakeInvoiceRepository(
            withGetInvoiceById = existingInvoice
        )

        val aViewModel = DetailsViewModel(
            invoiceId = Arb.long().filterNot { it == NEW_INVOICE_ID }.gen(),
            invoiceRepository = invoiceRepository
        )
        aViewModel.state shouldBe UiState.Viewing(
            invoice = existingInvoice,
            toolbarTitle = existingInvoice.title
        )
    }

    @Test
    fun `sets uiState as Error when viewModel is created with an invoiceId different than NEW_INVOICE_ID that does not exist`() = runTest {
        val invoiceId = Arb.long().filterNot { it == NEW_INVOICE_ID }.gen()
        val invoiceRepository = getFakeInvoiceRepository(
            withGetInvoiceById = null
        )

        val aViewModel = DetailsViewModel(
            invoiceId = invoiceId,
            invoiceRepository = invoiceRepository
        )
        aViewModel.state shouldBe UiState.Error(
            errorMessage = Res.string.invoice_details_error_initialising_view_invoice.format(
                invoiceId = invoiceId.toString()
            )
        )
    }

    @Test
    fun `sets uiState as Editing when onEditInvoiceClicked and current state is Viewing`() = runTest {
        val invoice = Arb.invoice().gen()
        val aViewModel = getDetailsViewModelWithStateViewing(expectedInvoice = invoice)

        aViewModel.onEditInvoiceClicked()

        aViewModel.state shouldBe UiState.Editing(
            invoice = invoice,
            toolbarTitle = invoice.title
        )
    }

    @Test
    fun `sets uiState as Editing with new Invoice when onEditInvoiceClicked and current state is Error`() = runTest {
        val newInvoice = Arb.invoice().gen()
        val aViewModel = getDetailsViewModelWithStateError(expectedNewInvoice = newInvoice)

        aViewModel.onEditInvoiceClicked()

        aViewModel.state shouldBe UiState.Editing(
            invoice = newInvoice,
            toolbarTitle = newInvoice.title
        )
    }

    @Test
    fun `updates uiState with new Invoice title when onEditInvoiceTitleChange`() {
        val newInvoice = Arb.invoice().gen()
        val invoiceRepository = getFakeInvoiceRepository(
            withGetNewInvoice = newInvoice
        )

        val aViewModel = DetailsViewModel(
            invoiceId = NEW_INVOICE_ID,
            invoiceRepository = invoiceRepository
        )
        val newTitle = Arb.string().gen()

        aViewModel.onEditInvoiceTitleChange(newTitle)

        aViewModel.state shouldBe UiState.Editing(
            invoice = newInvoice.copy(title = newTitle),
            toolbarTitle = newInvoice.title
        )
    }

    @Test
    fun `updates uiState with new Invoice description when onEditInvoiceDescriptionChange`() {
        val newInvoice = Arb.invoice().gen()
        val invoiceRepository = getFakeInvoiceRepository(
            withGetNewInvoice = newInvoice
        )

        val aViewModel = DetailsViewModel(
            invoiceId = NEW_INVOICE_ID,
            invoiceRepository = invoiceRepository
        )
        val newDescription = Arb.string().gen()

        aViewModel.onEditInvoiceDescriptionChange(newDescription)

        aViewModel.state shouldBe UiState.Editing(
            invoice = newInvoice.copy(description = newDescription),
            toolbarTitle = newInvoice.title
        )
    }

    private fun getDetailsViewModelWithStateViewing(expectedInvoice: Invoice): DetailsViewModel {
        val invoiceId = Arb.long().filterNot { it == NEW_INVOICE_ID }.gen()
        val invoiceRepository = getFakeInvoiceRepository(
            withGetInvoiceById = expectedInvoice
        )

        return DetailsViewModel(
            invoiceId = invoiceId,
            invoiceRepository = invoiceRepository
        )
    }

    private fun getDetailsViewModelWithStateError(expectedNewInvoice: Invoice): DetailsViewModel {
        val invoiceRepository = getFakeInvoiceRepository(
            withGetInvoiceById = null,
            withGetNewInvoice = expectedNewInvoice
        )

        return DetailsViewModel(
            invoiceId = Arb.long().filterNot { it == NEW_INVOICE_ID }.gen(),
            invoiceRepository = invoiceRepository
        )
    }
}

private fun getFakeInvoiceRepository(
    withGetNewInvoice: Invoice = Arb.invoice().gen(),
    withGetAllInvoices: List<Invoice> = Arb.invoice().genList(),
    withGetInvoiceById: Invoice? = Arb.invoice().genOrNull()
) = object : InvoiceRepository {
    override fun getNewInvoice(): Invoice = withGetNewInvoice
    override suspend fun getAllInvoices(): List<Invoice> = withGetAllInvoices
    override suspend fun getInvoiceById(invoiceId: Long): Invoice? = withGetInvoiceById
}
