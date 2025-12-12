package com.example.project_frontend.storeViewmodel


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_frontend.repository.StoreRepository
import com.example.project_frontend.schema.Favorite_products
import com.example.project_frontend.schema.ProductCard
import com.example.project_frontend.schema.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StoreViewModel(private val repository: StoreRepository) : ViewModel() {


    private var _userByEmail = mutableStateOf<User?>(null)

    val userByemail : State<User?> = _userByEmail



    var allUsers = mutableStateOf<List<User>>(emptyList())




    suspend fun SearchUserByEmail(email : String)
    {

            _userByEmail.value = repository.getUserByEmail(email)

    }

    fun AddUser(user : User)
    {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun GetAllUsers()
    {
        viewModelScope.launch {
            allUsers.value = repository.getAllUsers()
        }
    }


    //Favorite functions for the Ui


    fun addFavorite(product: Favorite_products) {
        viewModelScope.launch {
            repository.insertFavorite(product)
        }
    }


    suspend fun deleteFavorite(Favorite_id : Int)
    {
            repository.deletefavoriteProducts(Favorite_id)

    }

    val _favoriteProducts = mutableStateOf<List<Favorite_products>>(emptyList())
    val favoriteProducts: State<List<Favorite_products>> = _favoriteProducts





    suspend fun getAllFavoriteProducts() {
        try {
            _favoriteProducts.value = repository.getAllFavoritesProducts()
        } catch (e: Exception) {
            Log.e("StoreViewModel", "Failed to fetch favorites", e)
            _favoriteProducts.value = emptyList()
        }
    }


    //Card products


    fun AddCardProduct(card_product : ProductCard)
    {
        viewModelScope.launch {
            repository.AddCardProduct(card_product)
        }

    }



    fun DeleteCardProduct(productCardId : Int)
    {
        viewModelScope.launch {
            repository.DeleteCardProduct(productCardId)
        }
    }




    private val _cards = mutableStateOf<List<ProductCard>>(emptyList())
    val cards: State<List<ProductCard>> = _cards

    fun getAllCard() {
        viewModelScope.launch {
            repository.GetAllCards().collect { list ->
                _cards.value = list
            }
        }
    }


    // get one product



    private val _cardProduct = mutableStateOf<ProductCard?>(null)
    val cardProduct: State<ProductCard?> = _cardProduct

    suspend fun getCardProductById(product_id : Int)
    {

        _cardProduct.value = repository.getCardProductbyId(product_id)

    }




    // update the quantity

    fun updateQuantity(quantity : Int , productId: Int)
    {
        viewModelScope.launch {
            repository.updateQuantity(quantity , productId)
        }
    }


    // calculate le subtotal





    private val _subtotal = mutableStateOf<Double>(0.0)
    val subtotal: State<Double> = _subtotal


    fun getSubTotal(user_id : Int)
    {
        viewModelScope.launch{
           repository.getSubTotal(user_id).collect {
               _subtotal.value = it
           }
        }
    }




}