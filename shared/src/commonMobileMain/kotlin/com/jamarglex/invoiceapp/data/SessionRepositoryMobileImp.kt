package com.jamarglex.invoiceapp.data

import com.jamarglex.invoiceapp.domain.Session
import com.jamarglex.invoiceapp.domain.SessionRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

internal class SessionRepositoryMobileImp : SessionRepository {

    private var user: Session? = null

    override suspend fun isUserLoggedIn(): Boolean {
        return user != null
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Session> {

// TODO
//        este funciona ya...
//        ahora probar desktop o mejorar la navegacion y eso...(basicamente introducir firestore)




        return try {
            Firebase.auth.signInWithEmailAndPassword(email, password).user?.let { firebaseUser ->
                val session = Session(
                    id = firebaseUser.uid,
                    alias = firebaseUser.displayName ?: "",
                    email = firebaseUser.email ?: ""
                )
                user = session

                Result.success(session)
            } ?: Result.failure(NullPointerException("FirebaseUser is null after login"))
        } catch (e: Exception) {
            println("++Login Exception: $e")
            Result.failure(e)
        }
    }
}
