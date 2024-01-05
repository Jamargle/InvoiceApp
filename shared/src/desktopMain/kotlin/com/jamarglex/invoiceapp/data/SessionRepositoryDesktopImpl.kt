package com.jamarglex.invoiceapp.data

import com.jamarglex.invoiceapp.domain.Session
import com.jamarglex.invoiceapp.domain.SessionRepository

internal class SessionRepositoryDesktopImp : SessionRepository {

    private var user: Session? = null

    override suspend fun isUserLoggedIn(): Boolean {
        return user != null
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Session> {
//        return try {
//            Firebase.auth.signInWithEmailAndPassword(email, password).user?.let { firebaseUser ->
//                val session = Session(
//                    id = firebaseUser.uid,
//                    alias = firebaseUser.displayName ?: "",
//                    email = firebaseUser.email ?: ""
//                )
//                user = session

//              return  Result.success(Session(
//                    id = "asdasdasd",
//                    alias = "alias",
//                    email = "abc"
//                ))
//            } ?: Result.failure(NullPointerException("FirebaseUser is null after login"))
//        } catch (e: Exception) {
//            println("++Login Exception: $e")
          return Result.failure(NullPointerException("Ha habido un fallo balablablabla asda sd asd as"))
//        }
    }
}
