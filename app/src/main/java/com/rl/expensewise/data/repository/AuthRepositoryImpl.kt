package com.rl.expensewise.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.rl.expensewise.domain.model.AuthResult
import com.rl.expensewise.domain.model.ResetPasswordResult
import com.rl.expensewise.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
): AuthRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): AuthResult {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): AuthResult {
        TODO("Not yet implemented")
    }

    override suspend fun sendResetPasswordLink(email: String): ResetPasswordResult {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): FirebaseUser? {
        TODO("Not yet implemented")
    }


}