package com.jamarglex.invoiceapp.domain

import com.jamarglex.invoiceapp.data.SessionRepositoryMobileImp

actual fun getSessionRepository(): SessionRepository {
    return SessionRepositoryMobileImp()
}
