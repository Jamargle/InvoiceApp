package com.jamarglex.invoiceapp

import com.jamarglex.invoiceapp.domain.Invoice
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.enum
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.string

fun Arb.Companion.invoice(): Arb<Invoice> = arbitrary {
    Invoice(
        id = Arb.long().gen(),
        title = Arb.string().gen(),
        description = Arb.string().gen(),
        type = Arb.enum<Invoice.Type>().gen()
    )
}
