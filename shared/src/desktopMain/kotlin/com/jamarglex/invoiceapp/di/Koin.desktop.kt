package com.jamarglex.invoiceapp.di

import com.jamarglex.invoiceapp.data.SessionRepositoryDesktopImp
import com.jamarglex.invoiceapp.domain.SessionRepository
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<SessionRepository> { SessionRepositoryDesktopImp() }
}
