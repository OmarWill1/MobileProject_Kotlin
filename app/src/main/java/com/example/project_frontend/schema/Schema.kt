package com.example.project_frontend.schema

import android.media.Image
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "User" ,
    indices = [Index(value = ["email"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val name : String,
    val email: String ,
    val password: String
)


@Entity( tableName = "User_card")
data class ProductCard(
    @PrimaryKey(autoGenerate = true)
    val id_cart : Int = 0,
    val product_id :Int ,
    val name : String ,
    val Price : Double ,
    val quanity : Int = 1 ,
    val imageUrl: String ,
    val user_id : Int
)


@Entity(tableName = "User_table")
data class Favorite_products(
    @PrimaryKey(autoGenerate = true)
    val id_favorite : Int = 0 ,
    val product_id: Int ,
    val name: String,
    val description : String ,
    val price : Double ,
    val imageUrl: String ,
    val user_id: Int
)