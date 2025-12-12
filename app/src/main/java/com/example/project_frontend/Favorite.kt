package com.example.project_frontend


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project_frontend.storeViewmodel.StoreViewModel

@Composable
fun Favorite(navController: NavController , storeViewModel: StoreViewModel) {

    // Example state for items
    LaunchedEffect(Unit) {
        storeViewModel.getAllFavoriteProducts()
    }


    val items = storeViewModel.favoriteProducts.value// replace with your data

    Box(modifier = Modifier.fillMaxSize()) {

        // 1️⃣ Scrollable content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                // Add padding to avoid content being hidden by the fixed header and bottom navigation
                .padding(top = 70.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp) // A little less space can look cleaner
        ) {
            // The `items` block iterates through your list of favorite products.
            // `item.id` is used as a key for better performance and state preservation.
            items(items, key = { it.product_id }) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp) // Apply horizontal padding here
                        .clickable {
                            // Navigate to product detail screen, passing the product ID
                            navController.navigate("productDetail/${product.product_id}")
                        },
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White) // Set a clean background
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), // Padding inside the card
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Product Image using Coil
                        AsyncImage(
                            model = product.imageUrl,
                            contentDescription = "Image for ${product.name}",
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp)
                                .clip(RoundedCornerShape(8.dp)), // Rounded corners for the image
                            contentScale = ContentScale.Crop
                        )

                        // Product Details (Name and Price)
                        Column(
                            modifier = Modifier
                                .weight(1f) // Takes up remaining space
                                .padding(horizontal = 12.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = product.name, // Display the product name
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                maxLines = 2, // Prevent long names from breaking the layout
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "$${product.price}", // Display the product price
                                fontSize = 14.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }

                        // Favorite Icon/Button
                        IconButton(onClick = { /* Handle remove from favorites */ }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Remove from Favorites",
                                tint = Color.Red // Show it's a favorite
                            )
                        }
                    }
                }
            }
        }


        // Top fixed header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Favorites", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Icon(Icons.Default.Favorite, contentDescription = "Favorites")
        }

        // Bottom fixed navigation
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
