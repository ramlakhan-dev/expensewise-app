package com.rl.expensewise.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.rl.expensewise.domain.model.AuthResult
import com.rl.expensewise.domain.model.ResetPasswordResult
import com.rl.expensewise.domain.model.User
import com.rl.expensewise.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
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
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid
            if (uid == null) {
                return AuthResult.Error("Sign up failed: UID is null")
            }
            val user = User(
                id = uid,
                name = name,
                email = email
            )
            firebaseFirestore.collection("users").document(uid).set(user).await()
            AuthResult.Authenticated
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Sign up failed")
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): AuthResult {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthResult.Authenticated
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Sign in failed")
        }
    }

    override suspend fun sendResetPasswordLink(email: String): ResetPasswordResult {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            ResetPasswordResult.Success
        } catch (e: Exception) {
            ResetPasswordResult.Error(e.message ?: "Failed to send link")
        }
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): FirebaseUser? {
        TODO("Not yet implemented")
    }


}