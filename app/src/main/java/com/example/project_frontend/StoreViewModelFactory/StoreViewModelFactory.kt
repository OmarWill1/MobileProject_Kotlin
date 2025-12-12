package com.example.project_frontend.StoreViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.project_frontend.repository.StoreRepository
import com.example.project_frontend.storeViewmodel.StoreViewModel

class StoreViewModelFactory(private val repository: StoreRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StoreViewModel(repository) as T
    }
}