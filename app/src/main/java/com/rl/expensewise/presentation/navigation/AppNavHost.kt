package com.rl.expensewise.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rl.expensewise.presentation.screens.home.HomeScreen
import com.rl.expensewise.presentation.screens.resetpassword.ResetPasswordScreen
import com.rl.expensewise.presentation.screens.signin.SignInScreen
import com.rl.expensewise.presentation.screens.signup.SignUpScreen
import com.rl.expensewise.presentation.screens.welcome.WelcomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    isAuthenticated: Boolean
) {

    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated) Screen.HomeScreen.route else Screen.WelcomeScreen.route
    ) {

        composable(
            route = Screen.WelcomeScreen.route
        ) {
            WelcomeScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(
            route = Screen.SignUpScreen.route
        ) {
            SignUpScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(
            route = Screen.SignInScreen.route
        ) {
            SignInScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(
            route = Screen.ResetPasswordScreen.route
        ) {
            ResetPasswordScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(
                modifier = modifier,
                navController = navController
            )
        }
    }
}