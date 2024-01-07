package com.jamarglex.invoiceapp.data

import com.jamarglex.invoiceapp.domain.SessionRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

internal class SessionRepositoryDesktopImp : SessionRepository {

    override suspend fun isUserLoggedIn(): Boolean {
        return false // TODO implement this properly
    }

    suspend fun loginWithEmailAndPass(
        email: String,
        password: String
    ): String {
        return try {
            val authResult = Firebase.auth.signInWithEmailAndPassword(email, password)
            println("++signInWithEmailAndPassword Success")
            authResult.user!!.displayName!!
        } catch (e: Exception) {
            println("++Login Exception: $e")
            "Exception $e"
        }
    }
}
