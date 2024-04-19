package com.example.quickmart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickmart.data.api.RetrofitInstance
import com.example.quickmart.data.repositories.ProductRepositoryImplementation
import com.example.quickmart.data.repositories.SearchProductImplementation

class ProductViewModelFactory (): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(ProductRepositoryImplementation(RetrofitInstance.api)) as T
    }
}