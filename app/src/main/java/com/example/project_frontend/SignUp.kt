package com.example.project_frontend




import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController)
{
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("Page1")
                        {
                            launchSingleTop = true
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close, // <-- ImageVector here
                            contentDescription = "Cancel"
                        )
                    }} ,
                title = { Text("Sign up" ,
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


            Spacer(Modifier.height(10.dp))

            val name = remember { mutableStateOf("") }
            val email = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }


            Text("Name",
                Modifier.fillMaxWidth().padding(start = 10.dp),
                fontWeight = FontWeight.Bold)
            TextField(
                value = name.value ,
                onValueChange = {
                    name.value = it
                } ,
                label = { Text("Enter your name")},
                modifier = Modifier.fillMaxWidth().padding(10.dp) ,
                shape = RoundedCornerShape(10.dp),
                placeholder = {Text("Name")},
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xFFE0E0E0), // Renamed from containerColor
                    focusedContainerColor = Color(0xFFE0E0E0)   // Added for consistency
                )
            )
            Spacer(Modifier.height(12.dp))


            Text("Email",
                Modifier.fillMaxWidth().padding(start = 10.dp),
                fontWeight = FontWeight.Bold)
            TextField(
                value = email.value ,
                onValueChange = {
                    email.value = it
                } ,
                label = { Text("Enter your email")},
                modifier = Modifier.fillMaxWidth().padding(10.dp) ,
                shape = RoundedCornerShape(10.dp),
                placeholder = {Text("Email")},
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xFFE0E0E0), // Renamed from containerColor
                    focusedContainerColor = Color(0xFFE0E0E0)   // Added for consistency
                )


            )


            Spacer(Modifier.height(12.dp))


            Text("Password" ,
                Modifier.fillMaxWidth().padding(start = 10.dp),
                fontWeight = FontWeight.Bold)
            TextField(
                value = password.value ,
                onValueChange = {
                    password.value = it
                } ,
                label = { Text("Enter your Password")},
                modifier = Modifier.fillMaxWidth().padding(10.dp) ,
                shape = RoundedCornerShape(10.dp),
                placeholder = {Text("Password")},
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






            Spacer(modifier = Modifier.weight(1f))


            Button(onClick = {
                navController.navigate("Login")
                {
                    launchSingleTop = true
                }
            } ,
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00C8E3), // this is the button background
                    contentColor = Color.White          // this is the text/icon color
                )

            ) {

                Text("Sign Up")

            }














        }



    }
}






