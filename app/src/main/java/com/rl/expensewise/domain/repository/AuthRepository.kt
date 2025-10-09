package com.rl.expensewise.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.rl.expensewise.domain.model.AuthResult
import com.rl.expensewise.domain.model.ResetPasswordResult

interface AuthRepository {

    suspend fun signUp(name: String, email: String, password: String): AuthResult
    suspend fun signIn(email: String,  password: String): AuthResult
    suspend fun sendResetPasswordLink(email: String): ResetPasswordResult
    fun signOut()
    fun getCurrentUser(): FirebaseUser?
}