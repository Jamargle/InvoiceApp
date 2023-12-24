package com.jamarglex.invoiceapp.android

import android.app.Application
import com.jamarglex.invoiceapp.android.di.androidAppModule
import com.jamarglex.invoiceapp.di.initKoin
import com.jamarglex.invoiceapp.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class InvoiceAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@InvoiceAppApplication)
            androidLogger()
            modules(platformModule() + androidAppModule())
        }
    }

}
