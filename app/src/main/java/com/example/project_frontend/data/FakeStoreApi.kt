package com.example.project_frontend.data



import retrofit2.http.GET
import retrofit2.http.Path


interface FakeStoreApi
{
    @GET("products")
    suspend fun getproduts():List<Product>


    @GET("products/{id}")
    suspend fun getproductbyid(@Path("id" )id:Int): Product
}