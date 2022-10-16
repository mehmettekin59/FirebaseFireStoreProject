package com.mehmettekin.firebasefirestoreproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mehmettekin.firebasefirestoreproject.screens.LoginScreen
import com.mehmettekin.firebasefirestoreproject.screens.MainScreen
import com.mehmettekin.firebasefirestoreproject.screens.SignupScreen
import com.mehmettekin.firebasefirestoreproject.viewmodels.AuthViewModel
import com.mehmettekin.firebasefirestoreproject.viewmodels.CreateUserViewModel

@Composable
fun AppNavHost(
    userViewModel: CreateUserViewModel,
    viewModel: AuthViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Login.route
) {
    NavHost(navController = navController, startDestination = startDestination ){

        composable(Screen.Login.route){
            LoginScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.Signup.route){
            SignupScreen(navController = navController, viewModel = viewModel, userViewModel = userViewModel )
        }

        composable(Screen.Main.route){
            MainScreen()
        }

    }

}