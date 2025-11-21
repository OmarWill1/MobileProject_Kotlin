package com.example.project_frontend

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_frontend.ui.theme.Project_FrontEndTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page1(navController: NavController)
{

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SnowGear" ,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign= TextAlign.Center ,
                    fontWeight = FontWeight.Bold) },
                modifier = Modifier.statusBarsPadding().height(30.dp)
                // pushes content below status bar
            )
        }
    ) {
            innerPadding ->
        Column(modifier= Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally ) {

            Image(painter = painterResource(id = R.drawable.openwear2),
                contentDescription = "winter clothes picture " ,
                modifier = Modifier.fillMaxWidth().height(320.dp),
                contentScale = ContentScale.Crop)

            Spacer(modifier = Modifier.height(20.dp))

            Text("Welcome to Winter Sports" ,
                fontSize = 30.sp ,
                fontWeight = FontWeight.Bold ,
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(5.dp))

            Text("Discover the latest in winter sports apparel and gear. Get ready for your next adventure with our curated collection.",
                fontSize = 17.sp ,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.weight(1f)) // pushes button to bottom

            Button(onClick = {
                navController.navigate("Login")
                {
                    launchSingleTop = true
                }
            } ,
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00C8E3), // this is the button background
                    contentColor = Color.White          // this is the text/icon color
                )

            ) {

                Text("Get Started")

            }






        }



    }

}



