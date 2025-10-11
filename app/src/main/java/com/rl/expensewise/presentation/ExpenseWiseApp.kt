package com.rl.expensewise.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rl.expensewise.R
import com.rl.expensewise.presentation.navigation.AppNavHost
import com.rl.expensewise.presentation.navigation.Screen
import com.rl.expensewise.presentation.state.AuthState
import com.rl.expensewise.presentation.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseWiseApp() {

    val authViewModel: AuthViewModel = hiltViewModel()
    val userState by authViewModel.userState.collectAsState()

    val navController: NavHostController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val titleText = when(currentRoute) {
        Screen.SignUpScreen.route -> stringResource(id = R.string.sign_up)
        Screen.SignInScreen.route -> stringResource(id = R.string.sign_in)
        Screen.ResetPasswordScreen.route -> stringResource(id = R.string.reset_password)
        Screen.HomeScreen.route -> stringResource(id = R.string.app_name)
        else -> ""
    }

    Scaffold(
        topBar = {
            if (currentRoute != Screen.WelcomeScreen.route) {
                TopAppBar(
                    title = {
                        Text(
                            text = titleText,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        if (currentRoute != Screen.HomeScreen.route) {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(id = R.string.back)
                                )
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        AppNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            isAuthenticated = userState is AuthState.Authenticated
        )
    }

}