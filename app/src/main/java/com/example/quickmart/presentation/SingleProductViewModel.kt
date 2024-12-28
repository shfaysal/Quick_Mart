package com.example.quickmart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickmart.data.Result
import com.example.quickmart.data.models.Product
import com.example.quickmart.data.repositories.SingleProductRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SingleProductViewModel(
    private val singleProductRepository: SingleProductRepository
) : ViewModel() {

    private val _product = MutableStateFlow<Product>(Product.EMPTY)
    val product = _product.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            singleProductRepository.getSingleProduct().collectLatest { result ->
                when(result){
                    is Result.Error -> {
                        _showErrorToastChannel.send(false)
                    }
                    is Result.Success -> {
                        result.data?.let { product ->
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