package com.example.quickmart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickmart.data.api.RetrofitInstance
import com.example.quickmart.data.models.Product
import com.example.quickmart.data.repositories.PostProductRepositoryImplementation

class PostProductViewModelFactory(
    private  val product: Product
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostProductViewModel(PostProductRepositoryImplementation(RetrofitInstance.api,product)) as T
    }
}