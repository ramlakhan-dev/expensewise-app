package com.rl.expensewise.presentation.screens.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rl.expensewise.domain.model.AuthResult
import com.rl.expensewise.domain.model.ResetPasswordResult
import com.rl.expensewise.domain.usecase.auth.AuthUseCase
import com.rl.expensewise.presentation.state.AuthState
import com.rl.expensewise.presentation.state.ResetPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _resetPasswordState = MutableStateFlow<ResetPasswordState>(ResetPasswordState.Idle)
    val resetPasswordState: StateFlow<ResetPasswordState> = _resetPasswordState.asStateFlow()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val result = authUseCase.signInUseCase(email, password)
            _authState.value = when(result) {
                is AuthResult.Authenticated -> AuthState.Authenticated
                is AuthResult.Unauthenticated -> AuthState.Unauthenticated
                is AuthResult.Error -> AuthState.Error(result.message)
            }
        }
    }

    fun sendResetPasswordLink(email: String) {
        viewModelScope.launch {
            _resetPasswordState.value = ResetPasswordState.Loading

            val result = authUseCase.sendResetPasswordLinkUseCase(email)
            _resetPasswordState.value = when(result) {
                is ResetPasswordResult.Success -> ResetPasswordState.Success
                is ResetPasswordResult.Error -> ResetPasswordState.Error(result.message)
            }
        }
    }
}