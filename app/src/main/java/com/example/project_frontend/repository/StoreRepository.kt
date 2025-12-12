package com.example.project_frontend.repository

import com.example.project_frontend.dao.CardDao
import com.example.project_frontend.dao.UserDao
import com.example.project_frontend.dao.FavoriteProductsDao
import com.example.project_frontend.schema.Favorite_products
import com.example.project_frontend.schema.ProductCard
import com.example.project_frontend.schema.User
import kotlinx.coroutines.flow.Flow

class StoreRepository(private val userDao: UserDao , private val FavoriteProductsDao: FavoriteProductsDao , private val CardDao: CardDao) {

    suspend fun insertUser(user: User) {
        userDao.InsertUser(user)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }


    //Favorite insertion and maybe fitching




    suspend fun insertFavorite(product: Favorite_products) {
        FavoriteProductsDao.InsertFavoriteProduct(product)
    }

    suspend fun deletefavoriteProducts(Favorite_id : Int){
        FavoriteProductsDao.deleteFavoriteByProductId(Favorite_id)
    }


    suspend fun getAllFavoritesProducts(): List<Favorite_products>
    {
        return FavoriteProductsDao.selectAllFavoriteProducts()
    }



    //Card


    suspend fun AddCardProduct(card_product : ProductCard){
        CardDao.InsertCard(card_product)
    }


    suspend fun DeleteCardProduct(productID: Int)
    {
        CardDao.deleteCardByProductId(productID)
    }


    fun GetAllCards(): Flow<List<ProductCard>>
    {
        return CardDao.GetAllCard()
    }


    // get one product

    suspend fun getCardProductbyId(product_id : Int) : ProductCard
    {
        return CardDao.getCardProductById(product_id)
    }


    // update quantity

    suspend fun updateQuantity(quantity : Int , productId : Int)
    {
        CardDao.updateQuantity(quantity , productId)
    }




    fun getSubTotal(user_id : Int) : Flow<Double>
    {
         return CardDao.getSubtotal(user_id)
    }



}
