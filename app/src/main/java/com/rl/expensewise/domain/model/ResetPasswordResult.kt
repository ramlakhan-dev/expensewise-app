package com.rl.expensewise.domain.model

sealed class ResetPasswordResult {
    object Success: ResetPasswordResult()
    data class Error(val message: String): ResetPasswordResult()
}