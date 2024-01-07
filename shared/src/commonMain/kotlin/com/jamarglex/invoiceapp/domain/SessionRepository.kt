package com.jamarglex.invoiceapp.domain

interface SessionRepository {
    suspend fun isUserLoggedIn(): Boolean
    suspend fun login(email: String, password: String): Result<Session>
}

//expect fun getSessionRepository(): SessionRepository
