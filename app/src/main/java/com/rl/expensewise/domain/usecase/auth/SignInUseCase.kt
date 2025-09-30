package com.rl.expensewise.domain.usecase.auth

import com.rl.expensewise.domain.model.AuthResult
import com.rl.expensewise.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult = authRepository.signIn(email, password)
}