package com.example.project_frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun ProductDetail(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {

        // ----- SCROLLABLE CONTENT -----
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 10.dp, end = 10.dp, bottom = 80.dp)
        ) {

            item {
                Image(
                    painter = painterResource(id = R.drawable.page2),
                    contentDescription = "Image 3",
                    modifier = Modifier.height(350.dp).fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                Text(
                    "Summit Series Futurefleece Hoodie",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    "Ultra-light warmth with breathable performance for high-output mountain missions.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
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
                Button(
                    onClick = {
                        navController.navigate("Cart")
                        {
                            launchSingleTop = true
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

        // ----- TOP BAR -----
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
            }){Icon(Icons.Default.ArrowBack, contentDescription = "Back")}
            IconButton(onClick = {}){Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite") }
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
            ButtomApp()
        }
    }
}

