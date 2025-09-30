package com.rl.expensewise.domain.usecase.auth

import com.rl.expensewise.domain.model.AuthResult
import com.rl.expensewise.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String): AuthResult = authRepository.signUp(name, email, password)
}