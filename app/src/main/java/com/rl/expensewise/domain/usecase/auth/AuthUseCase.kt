package com.rl.expensewise.domain.usecase.auth

data class AuthUseCase (
    val signUpUseCase: SignUpUseCase,
    val signInUseCase: SignInUseCase,
    val signOutUseCase: SignOutUseCase,
    val getUserUseCase: GetUserUseCase,
    val sendResetPasswordLinkUseCase: SendResetPasswordLinkUseCase
)