package com.example.taskshift.presentation.ui.screens

import android.annotation.SuppressLint
import android.window.SplashScreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskshift.presentation.enums.ShiftScreenEnum
import com.example.taskshift.presentation.viewmodel.RegistrationViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun ShiftRegistrationApp(
    viewModel: RegistrationViewModel
) {
    val navController = rememberNavController()
    val isUserRegistered = viewModel.getUserName().isNotBlank()

    Scaffold {
        NavHost(
            navController = navController, startDestination = ShiftScreenEnum.Splash.name
        ) {
            composable(route = ShiftScreenEnum.Splash.name) {
                SplashScreen(navController, getStartDestination(isUserRegistered))
            }
            composable(route = ShiftScreenEnum.Registration.name) {
                RegistrationPage(viewModel, navController)
            }
            composable(route = ShiftScreenEnum.Greeting.name) {
                GreetingPage(viewModel)
            }
        }
    }
}

@Composable
private fun getStartDestination(isUserRegistered: Boolean): String {
    return if (isUserRegistered) ShiftScreenEnum.Greeting.name else ShiftScreenEnum.Registration.name
}
