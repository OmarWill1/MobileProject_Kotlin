package com.example.project_frontend


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.navigation.NavController


@Composable
fun Cart(navController: NavController) {

    // Data classes
    data class Clothes(val name: String, val size: String)
    data class Prices(val typePrice: String, val price: Double)

    Box(modifier = Modifier.fillMaxSize()) {

        // Sample data
        val clothes = listOf(
            Clothes("Snowboard Jacket", "Size M"),
            Clothes("Snowboard Boots", "Size 9"),
            Clothes("Ski Gloves", "Size M")
        )

        val prices = listOf(
            Prices("Subtotal", 350.0),
            Prices("Shipping", 10.0),
            Prices("Total", 360.0)
        )

        // ----- SCROLLABLE CONTENT -----
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Clothes list
            items(clothes) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.fillMaxHeight().align(Alignment.CenterStart), verticalArrangement = Arrangement.Center) {
                        Text(
                            item.name,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(item.size)
                    }

                    // Quantity row
                    val counter = remember { mutableStateOf(1) }
                    Row(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(onClick = { if (counter.value > 1) counter.value-- }) {
                            Text("-")
                        }
                        Text(
                            text = counter.value.toString(),
                            fontSize = 18.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Button(onClick = { counter.value++ }) {
                            Text("+")
                        }
                    }
                }
            }



            // Prices list
            items(prices) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.typePrice, fontSize = 18.sp)
                    Text("${item.price}", fontSize = 18.sp)
                }
            }

            // Checkout button


            item {

                // Exemple d'utilisation d'une variable nullable :
                // checkout peut être true, false ou null.
                // On teste sa valeur pour naviguer vers l'écran correspondant.

                val checkout = remember { mutableStateOf<Boolean?>(true) }
                Button(
                    onClick ={ if (checkout.value == true){
                        navController.navigate("OrderConfirmation")
                        {
                            launchSingleTop = true
                        }
                    }
                        else if(checkout.value == null ){
                            navController.navigate("OrderError")
                            {
                                launchSingleTop = true
                            }
                        }
                             },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(
                        "Checkout",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // ----- TOP HEADER -----
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .background(Color.White)
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.navigate("ProductDetail")
                {
                    launchSingleTop = true
                }
            })  {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                "Cart",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(start = 120.dp)
            )
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




