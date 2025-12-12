package com.example.project_frontend.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {
    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> = _products


    private val _product = mutableStateOf<Product?>(null)

    val product : State<Product?> = _product


    suspend fun loadProducts() {
            val products = productRepository.getProducts()
            _products.value = products
    }



    suspend fun loadProductByid(id_product: Int)
    {
        _product.value = productRepository.getProductByid(id_product)

    }
}