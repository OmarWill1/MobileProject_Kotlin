package com.example.project_frontend


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.project_frontend.repository.StoreRepository




import com.example.project_frontend.StoreDB.AppDatabase
import com.example.project_frontend.StoreViewModelFactory.StoreViewModelFactory
import com.example.project_frontend.data.ProductRepository
import com.example.project_frontend.data.ProductViewModel
import com.example.project_frontend.data.ProductViewModelFactory
import com.example.project_frontend.storeViewmodel.StoreViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val db = AppDatabase.getInstance(this)


        val productRepo = ProductRepository()  // or inject it
        val productViewModel = ViewModelProvider(
            this,
            ProductViewModelFactory(productRepo)
        ).get(ProductViewModel::class.java)


        val repo = StoreRepository(db.storeDao(), db.favoriteProductsDao(), CardDao = db.CardDao()  )
        val viewModel = ViewModelProvider(this, StoreViewModelFactory(repo))
            .get(StoreViewModel::class.java)






        setContent {


            AppNavigation(viewModel , productViewModel)





        }
    }
}

@Composable
fun ScreenFavorite(viewModel: StoreViewModel , productViewModel: ProductViewModel)
{
    LaunchedEffect(Unit) {
        productViewModel.loadProducts()
    }

    val allProducts = viewModel.favoriteProducts.value


    LazyColumn {
            items(allProducts) { item ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(item.name)
                    Text(item.description)
                    Text(item.price.toString())
                }
            }
        }


}