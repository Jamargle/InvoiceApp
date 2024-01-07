package com.jamarglex.invoiceapp.di

import com.jamarglex.invoiceapp.data.InvoiceRepositoryImp
import com.jamarglex.invoiceapp.data.api.NetworkInvoiceDataSource
import com.jamarglex.invoiceapp.domain.InvoiceRepository
import com.jamarglex.invoiceapp.ui.details.DetailsViewModel
import com.jamarglex.invoiceapp.ui.home.HomeViewModel
import com.jamarglex.invoiceapp.ui.login.LoginViewModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        modules(commonModule())
        appDeclaration()
    }

// called by iOS client
fun initKoin() = initKoin {}

fun commonModule() = module {
    includes(platformModule())
    factory { Firebase.firestore }
    factory { NetworkInvoiceDataSource(get()) }
    factory<InvoiceRepository> { InvoiceRepositoryImp(get()) }

    factory {
        LoginViewModel(
            sessionRepository = get()
        )
    }
    factory {
        HomeViewModel(
            sessionRepository = get(),
            invoiceRepository = get()
        )
    }
    factory { (invoiceId: Long) ->
        DetailsViewModel(
            invoiceId = invoiceId,
            invoiceRepository = get()
        )
    }
}
