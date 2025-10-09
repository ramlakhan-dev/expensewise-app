package com.rl.expensewise.domain.usecase.auth

import com.google.firebase.auth.FirebaseUser
import com.rl.expensewise.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): FirebaseUser? = authRepository.getCurrentUser()
}