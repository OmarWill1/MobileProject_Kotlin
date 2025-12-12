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
import com.example.project_frontend.data.Product
import com.example.project_frontend.data.ProductViewModel
















@Composable
fun ButtomApp(navController: NavController)
{





    val titles = listOf("Home", "Shop", "Cart" , "Favorite")
    val icons = listOf(Icons.Default.Home, Icons.Default.Store, Icons.Default.ShoppingCart,Icons.Default.Favorite)
    val routes = listOf("Page1" , "Shop" , "Cart" , "Favorite")

    var selected by remember { mutableStateOf("Shop") }



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterHorizontally) // space + center
    ) {
        for (i in titles.indices) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = {
                    selected = titles[i]
                    navController.navigate(routes[i])
                    {
                        launchSingleTop = true
                    }

                }) {
                    Icon(
                        imageVector = icons[i],
                        contentDescription = titles[i],
                        tint = if (selected == titles[i]) Color.Black else Color.Gray
                    )
                }

                Text(text = titles[i],
                    fontSize = 12.sp,
                    color = if (selected == titles[i]) Color.Black else Color.Gray)
            }

        }
    }
}


data class ClothingItem( val name: String,
                         val price: String,
                         val imageRes: Int )



@Composable
fun Shop(navController: NavController , productViewModel: ProductViewModel) {
    // Sample data

    val scope = rememberCoroutineScope()
    val typeClothes = listOf("All", "Men", "Women", "Kids")




    // Get the State object directly
    val products  by productViewModel.products

// No .collectAsState() is needed!

    LaunchedEffect(Unit) {
            productViewModel.loadProducts()
    }





    Box(modifier = Modifier.fillMaxSize()) {



        // 1️⃣ Scrollable content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp), // reserve space for header & footer
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Type buttons
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    typeClothes.forEach { type ->
                        Button(onClick = {}) {
                            Text(text = type, fontSize = 10.sp)
                        }
                    }
                }
            }

            // Product cards

            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp).padding(8.dp)
                        .let{
                                mod ->

                                mod.clickable{
                                    navController.navigate("ProductDetail/${product.id}"){
                                        popUpTo("route_to_clear_up_to") {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                 }
                            }

                        ,
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(contentColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Text Column
                        Column( Modifier.padding(start =8.dp )
                            .weight(1f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${product.title ?: ""}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black,
                                maxLines = 1 ,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "${product.price ?:0}",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Image
                       AsyncImage(
                            model = product.image ?: "",
                            contentDescription = product.description ?: "",
                            modifier = Modifier
                                .width(140.dp)
                                .height(90.dp)
                                .padding(end = 8.dp)
                                .clip
                                    (RoundedCornerShape(12.dp)),
                            contentScale =  ContentScale.Crop

                        )
                    }
                }
            }
        }


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
            Text("Shop", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Icon(Icons.Default.Search, contentDescription = "Search")
        }

        //  Fixed Footer
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




