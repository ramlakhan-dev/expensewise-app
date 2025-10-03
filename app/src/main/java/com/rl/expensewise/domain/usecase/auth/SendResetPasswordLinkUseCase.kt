package com.rl.expensewise.domain.usecase.auth

import com.rl.expensewise.domain.model.ResetPasswordResult
import com.rl.expensewise.domain.repository.AuthRepository
import javax.inject.Inject

class SendResetPasswordLinkUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String): ResetPasswordResult = authRepository.sendResetPasswordLink(email)
}