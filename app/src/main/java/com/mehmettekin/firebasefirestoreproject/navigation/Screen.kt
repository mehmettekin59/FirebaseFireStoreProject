package com.mehmettekin.firebasefirestoreproject.navigation

sealed class Screen(val route: String){
    object Login: Screen(route = "login")
    object Signup: Screen(route = "signup")
    object Main: Screen(route = "main")
}
