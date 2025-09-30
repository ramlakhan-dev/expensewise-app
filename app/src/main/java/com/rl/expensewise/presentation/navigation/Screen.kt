package com.rl.expensewise.presentation.navigation

sealed class Screen(val route: String) {
    object WelcomeScreen: Screen(route = "welcome")
    object SignInScreen: Screen(route = "sign-in")
    object SignUpScreen: Screen(route = "sign-up")
    object HomeScreen: Screen(route = "home")
}