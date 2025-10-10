package com.rl.expensewise.presentation.screens.resetpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rl.expensewise.R
import com.rl.expensewise.presentation.components.InputTextField
import com.rl.expensewise.presentation.components.PrimaryButton
import com.rl.expensewise.presentation.navigation.Screen
import com.rl.expensewise.presentation.state.AuthState
import com.rl.expensewise.presentation.state.ResetPasswordState

@Composable
fun ResetPasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel()
    val resetPasswordState by resetPasswordViewModel.resetPasswordState.collectAsState()
    LaunchedEffect(resetPasswordState) {
        if (resetPasswordState is ResetPasswordState.Success) {
            navController.popBackStack()
        }
    }
    var email by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            InputTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                placeholder = stringResource(id = R.string.email),
                leadingIcon = Icons.Default.Email,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done

                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (resetPasswordState is ResetPasswordState.Loading) {
                CircularProgressIndicator()
            }

            if (resetPasswordState is ResetPasswordState.Error) {
                Text(
                    text = (resetPasswordState as ResetPasswordState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        PrimaryButton(
            text = stringResource(id = R.string.send),
            onClick = {
                resetPasswordViewModel.sendResetPasswordLink(email)
            },
            enabled = email.isNotEmpty()
        )
    }
}