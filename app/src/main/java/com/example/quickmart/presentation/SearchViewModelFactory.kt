package com.example.quickmart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickmart.data.api.RetrofitInstance
import com.example.quickmart.data.repositories.ProductRepositoryImplementation
import com.example.quickmart.data.repositories.SearchProductImplementation

class SearchViewModelFactory (
    private val searchText : String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchProductViewModel(SearchProductImplementation(RetrofitInstance.api, searchText )) as T
    }
}