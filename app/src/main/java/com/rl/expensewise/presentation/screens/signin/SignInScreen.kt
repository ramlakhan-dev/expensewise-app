package com.rl.expensewise.presentation.screens.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rl.expensewise.R
import com.rl.expensewise.presentation.components.OutlinedInputText
import com.rl.expensewise.presentation.components.PrimaryButton
import com.rl.expensewise.presentation.navigation.Screen
import com.rl.expensewise.presentation.state.AuthState

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val signInViewModel: SignInViewModel = hiltViewModel()
    val authState by signInViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            navController.navigate(route = Screen.HomeScreen.route) {
                popUpTo(route = Screen.WelcomeScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }


    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        if (authState is AuthState.Loading) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column {
                OutlinedInputText(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    placeholder = stringResource(id = R.string.email),
                    leadingIcon = Icons.Default.Email,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedInputText(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    placeholder = stringResource(id = R.string.password),
                    isPassword = true,
                    leadingIcon = Icons.Default.Lock,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )

                Text(
                    text = stringResource(id = R.string.forgot_password),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.End
                )

                if (authState is AuthState.Error) {
                    Text(
                        text = (authState as AuthState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            PrimaryButton(
                text = stringResource(id = R.string.sign_in),
                onClick = {
                    signInViewModel.signIn(email, password)
                },
                enabled = email.isNotEmpty() && password.isNotEmpty()
            )
        }
    }
}