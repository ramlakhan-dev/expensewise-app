package com.rl.expensewise.presentation.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rl.expensewise.R
import com.rl.expensewise.presentation.components.PrimaryButton
import com.rl.expensewise.presentation.components.SecondaryButton
import com.rl.expensewise.presentation.navigation.Screen

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_app_logo),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .size(56.dp)
            )

            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.welcome_message),
                fontSize = 16.sp
            )
        }

        Column {
            SecondaryButton(
                text = stringResource(id = R.string.sign_in),
                onClick = {
                    navController.navigate(route = Screen.SignInScreen.route)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = stringResource(id = R.string.sign_up),
                onClick = {
                    navController.navigate(route = Screen.SignUpScreen.route)
                }
            )
        }
    }
}