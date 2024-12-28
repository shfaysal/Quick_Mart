package com.example.quickmart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickmart.data.api.RetrofitInstance
import com.example.quickmart.data.repositories.SingleProductRepositoryImplementation

class SingleProductViewModelFactory (
    private val id : Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SingleProductViewModel(SingleProductRepositoryImplementation(RetrofitInstance.api, id)) as T
    }
}