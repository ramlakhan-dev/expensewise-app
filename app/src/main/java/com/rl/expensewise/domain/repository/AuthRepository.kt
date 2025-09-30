package com.rl.expensewise.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.rl.expensewise.domain.model.AuthResult

interface AuthRepository {

    suspend fun signUp(name: String, email: String, password: String): AuthResult
    suspend fun signIn(email: String, password: String): AuthResult

    fun signOut()
    fun getCurrentUser(): FirebaseUser?
}