package com.rl.expensewise.presentation.navigation

sealed class Screen(val route: String) {
    object WelcomeScreen: Screen(route = "welcome-screen")
    object SignUpScreen: Screen(route = "sign-up-screen")
    object SignInScreen: Screen(route = "sign-in-screen")
    object ResetPasswordScreen: Screen(route = "reset-password-screen")
    object HomeScreen: Screen(route = "home-screen")
}