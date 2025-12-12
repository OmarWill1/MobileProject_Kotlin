package com.example.project_frontend

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project_frontend.storeViewmodel.StoreViewModel


@Composable
fun UsersShowing(viewModel : StoreViewModel , navController: NavController)
{

    val scope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize().padding(8.dp)) {



        viewModel.GetAllUsers()
        val users = viewModel.allUsers.value
        Text("All users from the database" ,
            fontSize = 20.sp ,
            fontWeight = FontWeight.Bold)


        LazyColumn {
            items(users)
            {
                user ->
                Row {
                    Text(user.name)
                    Text(user.email)
                    Text(user.password)
                }
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}