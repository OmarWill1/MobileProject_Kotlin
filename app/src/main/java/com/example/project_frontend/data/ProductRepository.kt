package com.example.project_frontend.data

class ProductRepository
{
    suspend fun getProducts(): List<Product>

    {
        return RetrofitInstance.api.getproduts()

    }


    suspend fun getProductByid(id_product : Int): Product
    {

        return RetrofitInstance.api.getproductbyid(id_product)
    }


}
