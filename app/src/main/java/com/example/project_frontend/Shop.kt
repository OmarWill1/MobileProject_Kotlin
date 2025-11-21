package com.example.project_frontend

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.reflect.typeOf
















@Composable
fun ButtomApp()
{





    val titles = listOf("Home", "Shop", "Cart" , "Profile")
    val icons = listOf(Icons.Default.Home, Icons.Default.Store, Icons.Default.ShoppingCart,Icons.Default.Person)

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
                IconButton(onClick = { selected = titles[i] }) {
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
fun Shop(navController: NavController) {
    // Sample data
    val typeClothes = listOf("All", "Men", "Women", "Kids")
    val clothesDetails = listOf(
        ClothingItem("Base Layer", "80$", R.drawable.pic1),
        ClothingItem("Insulated Jacket", "250$", R.drawable.page2),
        ClothingItem("Snow Pants", "180$", R.drawable.page3),
        ClothingItem("Gloves", "50$", R.drawable.page4),
        ClothingItem("Futurefleece Hoodie", "150$", R.drawable.page5)
    )

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
            items(clothesDetails) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp).padding(8.dp)
                        .let{
                                mod ->
                            if (item.name == "Futurefleece Hoodie"){
                                mod.clickable{
                                    navController.navigate("ProductDetail"){
                                        launchSingleTop = true
                                    }
                                }
                                }
                            else mod
                            }

                        ,
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(contentColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize().padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Text Column
                        Column( Modifier.padding(start =8.dp ) ,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = item.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Text(
                                text = item.price,
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Image
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = item.name,
                            modifier = Modifier
                                .width(140.dp)
                                .height(90.dp).padding(end = 8.dp).clip
                                    (RoundedCornerShape(12.dp)),
                            contentScale =  ContentScale.Crop

                        )
                    }
                } // your composable card
            }
        }

        //  Fixed Header
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
            ButtomApp()
        }
    }
}




