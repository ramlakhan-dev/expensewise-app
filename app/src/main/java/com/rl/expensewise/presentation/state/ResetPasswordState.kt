package com.rl.expensewise.presentation.state

sealed class ResetPasswordState {
    object Idle: ResetPasswordState()
    object Loading: ResetPasswordState()
    object Success: ResetPasswordState()
    data class Error(val message: String): ResetPasswordState()
}