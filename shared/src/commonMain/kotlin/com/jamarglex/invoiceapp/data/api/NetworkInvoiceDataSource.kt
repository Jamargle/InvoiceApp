package com.jamarglex.invoiceapp.data.api

import com.jamarglex.invoiceapp.data.api.model.Invoice
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore

internal class NetworkInvoiceDataSource(
    private val firebaseFirestore: FirebaseFirestore
) {
    suspend fun getAllInvoices(): Result<List<Invoice>> {
        return try {
            val invoicesResponse = firebaseFirestore.collection("INVOICES").get()
            val invoices: List<Invoice> = invoicesResponse.documents
                .map { it.data() }
            Result.success(invoices)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
