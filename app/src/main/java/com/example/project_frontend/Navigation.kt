package com.example.project_frontend

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.project_frontend.data.ProductViewModel
import com.example.project_frontend.storeViewmodel.StoreViewModel


@Composable
fun AppNavigation(viewModel: StoreViewModel , productViewModel: ProductViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Shop") {
        composable(route = "page1") { Page1(navController) }
        composable(route = "SignUp") { SignUp(navController , viewModel = viewModel) }
        composable(route = "Login") {Login(navController, viewModel = viewModel)}

        composable(
            route = "Shop"
        ) {
            Shop(
                navController = navController,
                productViewModel = productViewModel
            )
        }

        composable(
            route = "ProductDetail/{id}"
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt()


            if (id != null) {
                ProductDetail(
                    navController = navController,
                    viewModel = viewModel,
                    productViewModel = productViewModel,
                    productId = id
                )
            }

        }


        composable(route = "Cart"){Cart(navController , viewModel)}
        composable(route="OrderConfirmation"){OrderConfirmation(navController)}
        composable(route = "OrderError"){OrderError(navController)}
        composable(route="UsersShowing"){UsersShowing(viewModel = viewModel , navController = navController)}
        composable(route="Favorite"){Favorite(navController , viewModel)}

    }
}
