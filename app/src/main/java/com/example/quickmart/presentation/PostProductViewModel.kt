package com.example.quickmart.presentation

import android.text.BoringLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickmart.data.Result
import com.example.quickmart.data.models.Product
import com.example.quickmart.data.repositories.PostProductRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostProductViewModel(
    private val postProductRepository: PostProductRepository,
) : ViewModel() {


    private val _product = MutableStateFlow<Product>(Product("sazzad","sazzad","sdsdfs",10.9,3,
        listOf("sdfsdfs","sdsf"),34.3,34.3,3,"sfdsfd","sdfsfd"))
    val product = _product.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            postProductRepository.postProduct().collectLatest { result ->
                when(result) {
                    is Result.Error -> {
                        _showErrorToastChannel.send(false)
                    }
                    is Result.Success -> {
                        result.data?.let {product ->
                            _product.update {
                                product
                            }
                        }
                    }
                }
            }
        }
    }
}