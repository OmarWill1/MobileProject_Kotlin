package com.example.project_frontend


import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project_frontend.data.ProductViewModel
import com.example.project_frontend.schema.Favorite_products
import com.example.project_frontend.schema.ProductCard
import com.example.project_frontend.storeViewmodel.StoreViewModel
import kotlinx.coroutines.launch


@Composable
fun ProductDetail(navController: NavController , viewModel: StoreViewModel ,productViewModel: ProductViewModel, productId: Int) {


    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {


        // ----- SCROLLABLE CONTENT -----

        val product = productViewModel.product.value






        LaunchedEffect(Unit) {
            productViewModel.loadProductByid(productId)
        }

        if (product != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp, start = 10.dp, end = 10.dp, bottom = 80.dp)
            ) {

                item {
                    AsyncImage(
                        model = product.image,
                        contentDescription = product.description,
                        modifier = Modifier.height(350.dp).fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Text(
                        product.title ?: "",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(10.dp))

                    //description
                    Text(
                        product.description ?: "",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,

                        )
                    Spacer(Modifier.height(20.dp))
                }

                item {
                    Text("Size", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(7.dp))
                    val listSize = listOf("S", "M", "L", "XL")

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        listSize.forEach { size ->
                            Button(onClick = {}) {
                                Text(text = size)
                            }
                        }
                    }

                    Spacer(Modifier.height(10.dp))
                }

                item {
                    Text("Color", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(7.dp))
                    val listColor = listOf("Black", "Blue", "Red")

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        listColor.forEach { colorName ->
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(
                                        color = when (colorName.lowercase()) {
                                            "black" -> Color.Black
                                            "blue" -> Color.Blue
                                            "red" -> Color.Red
                                            else -> Color.Gray
                                        },
                                        shape = CircleShape
                                    )
                            )
                        }
                    }

                    Spacer(Modifier.height(18.dp))
                }

                item {

                    val ClickedCard = remember { mutableStateOf(false) }
                    Button(
                        onClick = {


                            val ProductCard = ProductCard(
                                product_id = product.id,
                                name = product.title,
                                Price = product.price,
                                imageUrl = product.image ,
                                user_id = 1
                            )


                            scope.launch {

                                viewModel.getCardProductById(product.id)
                                if(viewModel.cardProduct.value == null)
                                {
                                    viewModel.AddCardProduct(ProductCard)
                                    Toast.makeText(context, "Product added succesefully", Toast.LENGTH_SHORT).show()
                                    navController.navigate("Cart")
                                    {
                                        popUpTo("route_to_clear_up_to") {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                }


                                else {
                                    Toast.makeText(context, "Product already in cart", Toast.LENGTH_SHORT).show()
                                }

                                ClickedCard.value = true

                            }


                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                    ) {
                        Text(
                            "Add to cart",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        val isClicked = remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()


        // ----- TOP BAR -----

        if (product != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .statusBarsPadding()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    navController.navigate("Shop")
                    {
                        launchSingleTop = true
                    }
                }
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }


                // add the product into the database favorite

                IconButton(onClick = {
                    val favoriteProduct = Favorite_products(
                        product_id = product.id,
                        name = product.title,
                        description = product.description,
                        price = product.price,
                        imageUrl = product.image,
                        user_id = 1
                    )

                    isClicked.value = !isClicked.value


                    if (isClicked.value) {

                        viewModel.addFavorite(favoriteProduct)
                    } else {
                        scope.launch { viewModel.deleteFavorite(favoriteProduct.id_favorite) }   // FIXED HERE
                    }

                }
                ) {
                    Icon(
                        imageVector = if (isClicked.value) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                        contentDescription = "Favorite",
                        tint = if (isClicked.value) Color.Red else Color.Unspecified
                    )
                }
            }

        }





        // ----- FOOTER -----
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomCenter)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtomApp(navController)
        }
    }
}

