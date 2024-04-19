package com.example.quickmart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickmart.data.Result
import com.example.quickmart.data.models.Product
import com.example.quickmart.data.repositories.ProductRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel (
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _showErrorTestChannel = Channel<Boolean>()
    val showErrorTestChannel = _showErrorTestChannel.receiveAsFlow()


    init {
        viewModelScope.launch {
            productRepository.getProductList().collectLatest { result ->

                when(result) {
                    is Result.Error -> {
                        _showErrorTestChannel.send(false)
                    }

                    is Result.Success -> {
                        result.data?.let {products ->
                            _products.update { products }
                        }
                    }
                }

            }
        }
    }


}