package com.example.project_frontend.StoreDB



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project_frontend.dao.CardDao
import com.example.project_frontend.schema.User
import com.example.project_frontend.schema.Favorite_products
import com.example.project_frontend.dao.UserDao
import com.example.project_frontend.dao.FavoriteProductsDao
import com.example.project_frontend.schema.ProductCard

@Database(entities = [User::class , Favorite_products::class , ProductCard::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storeDao(): UserDao
    abstract fun favoriteProductsDao(): FavoriteProductsDao


    abstract fun CardDao() : CardDao



    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "store_db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}
