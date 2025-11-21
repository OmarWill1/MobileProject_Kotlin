package com.example.project_frontend

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController




@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "page1") {
        composable(route = "page1") { Page1(navController) }
        composable(route = "SignUp") { SignUp(navController) }
        composable(route = "Login") {Login(navController)}
        composable(route = "Shop"){Shop(navController)}
        composable(route = "ProductDetail"){ProductDetail(navController)}
        composable(route = "Cart"){Cart(navController)}
        composable(route="OrderConfirmation"){OrderConfirmation(navController)}
        composable(route = "OrderError"){OrderError(navController)}

    }
}
