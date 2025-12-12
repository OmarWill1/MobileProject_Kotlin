package com.example.project_frontend.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project_frontend.schema.Favorite_products
import com.example.project_frontend.schema.ProductCard
import com.example.project_frontend.schema.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao{


    @Insert
    suspend fun InsertUser(user : User)


    @Query("Select * from User where email = :email LIMIT 1 ")
    suspend fun getUserByEmail(email: String) : User?


    @Query("Select * from User")
    suspend fun getAllUsers() : List<User>





}


@Dao
interface FavoriteProductsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertFavoriteProduct(product: Favorite_products)



    @Query("DELETE FROM User_table WHERE product_id = :productId")
    suspend fun deleteFavoriteByProductId(productId: Int)


    @Query("SELECT * FROM User_table")
    suspend fun selectAllFavoriteProducts(): List<Favorite_products>


}


@Dao
interface CardDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertCard(card: ProductCard)

    @Query("SELECT * FROM user_card")
    fun GetAllCard(): Flow<List<ProductCard>>


    @Query("SELECT * FROM user_card where product_id = :product_id")
    suspend fun getCardProductById(product_id : Int) : ProductCard

    @Query("DELETE FROM user_card WHERE product_id = :productId")
    suspend fun deleteCardByProductId(productId: Int)


    //Update quantity
    @Query("update user_card set quanity = :quantity where product_id = :productId")
    suspend fun updateQuantity(quantity: Int ,productId : Int)


    // SUBTOTAL

    @Query("SELECT SUM(quanity * price) from user_card where user_id = :user_id ")
    fun getSubtotal(user_id : Int) : Flow<Double>


}