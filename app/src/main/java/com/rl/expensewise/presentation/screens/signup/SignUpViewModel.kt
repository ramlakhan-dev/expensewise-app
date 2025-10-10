package com.rl.expensewise.presentation.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rl.expensewise.domain.model.AuthResult
import com.rl.expensewise.domain.usecase.auth.AuthUseCase
import com.rl.expensewise.presentation.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val result = authUseCase.signUp(name, email, password)
            _authState.value = when(result) {
                is AuthResult.Authenticated -> AuthState.Authenticated
                is AuthResult.Unauthenticated -> AuthState.Unauthenticated
                is AuthResult.Error -> AuthState.Error(result.message)
            }
        }
    }
}