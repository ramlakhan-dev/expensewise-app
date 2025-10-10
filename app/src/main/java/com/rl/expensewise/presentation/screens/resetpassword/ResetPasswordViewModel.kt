package com.rl.expensewise.presentation.screens.resetpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rl.expensewise.domain.model.ResetPasswordResult
import com.rl.expensewise.domain.usecase.auth.AuthUseCase
import com.rl.expensewise.presentation.state.ResetPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): ViewModel() {

    private val _resetPasswordState = MutableStateFlow<ResetPasswordState>(ResetPasswordState.Idle)
    val resetPasswordState: StateFlow<ResetPasswordState> = _resetPasswordState.asStateFlow()

    fun sendResetPasswordLink(email: String) {
        viewModelScope.launch {
            _resetPasswordState.value = ResetPasswordState.Loading

            val result = authUseCase.sendResetPasswordLink(email)
            _resetPasswordState.value = when(result) {
                is ResetPasswordResult.Success -> ResetPasswordState.Success
                is ResetPasswordResult.Error -> ResetPasswordState.Error(result.message)
            }
        }
    }
}