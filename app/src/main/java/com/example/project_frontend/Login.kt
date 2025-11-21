package com.example.project_frontend

import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController)
{
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("SignUp")
                        {
                            launchSingleTop = true
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, // <-- ImageVector here
                            contentDescription = "Back"
                        )
                    }} ,
                title = { Text("Login" ,
                    modifier = Modifier.fillMaxWidth().padding(start = 110.dp),
                    textAlign= TextAlign.Start ,
                    fontWeight = FontWeight.Bold) },
                modifier = Modifier.statusBarsPadding().height(50.dp)
                // pushes content below status bar
            )
        }
    ) {
            innerPadding ->
        Column(modifier= Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally ) {


            val email = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }



            Spacer(Modifier.height(10.dp))

            TextField(
                value = email.value ,
                onValueChange = {
                    email.value = it
                } ,
                label = { Text("Email")},
                modifier = Modifier.fillMaxWidth().padding(10.dp) ,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xFFE0E0E0), // Renamed from containerColor
                    focusedContainerColor = Color(0xFFE0E0E0)   // Added for consistency
                )


            )



            TextField(
                value = password.value ,
                onValueChange = {
                    password.value = it
                } ,
                label = { Text("Password")},
                modifier = Modifier.fillMaxWidth().padding(10.dp) ,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xFFE0E0E0), // Renamed from containerColor
                    focusedContainerColor = Color(0xFFE0E0E0),
                    // Added for consistency
                ),
                visualTransformation = PasswordVisualTransformation(), // this hides the text as dots
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true


            )


            Button(onClick = {
                navController.navigate("Shop"){
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

                Text("Login")

            }

            Spacer(Modifier.height(50.dp))


            Text("Don't have an account ? Sign Up" ,
                modifier = Modifier.fillMaxWidth().padding(10.dp).clickable{
                    navController.navigate("SignUp")
                    {
                        launchSingleTop = true
                    }
                },
                textAlign = TextAlign.Center ,
                fontWeight = FontWeight.W400 ,
                color = Color.Gray ,
                fontSize = 15.sp
            )







        }



    }
}




