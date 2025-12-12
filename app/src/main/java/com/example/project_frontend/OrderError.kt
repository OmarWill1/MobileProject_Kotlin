package com.example.project_frontend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun OrderError(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {

        // ----- CONTENT -----
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp , bottom = 70.dp)
        ) {



            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {

                // Green circle
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .background(Color(0xFFD32F2F), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // White ✓ checkmark
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Success",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Text(
                text = "Something went wrong. Please try again.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )


            Button(
                onClick = {
                    navController.navigate("OrderConfirmation"){
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color((0xFFD32F2F))
                )

            ) {
                Text(
                    "Try Again",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
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
            IconButton(onClick = {})  {
                IconButton(  onClick = { navController.navigate("Cart"){
                    launchSingleTop = true
                } }) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
            }
            Text(
                text = "Order Error",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp)
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
            ButtomApp(navController)
        }
    }


}



