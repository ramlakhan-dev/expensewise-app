package com.rl.expensewise.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rl.expensewise.presentation.navigation.AppNavHost

@Composable
fun App() {

    val navController: NavHostController = rememberNavController()

    Scaffold { innerPadding ->
        AppNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            isAuthenticated = false
        )
    }
}