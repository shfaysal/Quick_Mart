package com.example.quickmart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickmart.data.Result
import com.example.quickmart.data.repositories.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel (
    private val userRepository: UserRepository
) : ViewModel(){

    private val _response = MutableStateFlow<Boolean>(false)
    val response = _response.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            userRepository.getUserDetail().collectLatest { result ->
                when(result) {
                    is Result.Error -> {
                        _showErrorToastChannel.send(false)
                    }

                    is Result.Success -> {
                        result.data?.let {response ->
                            _response.update {
                                response
                            }
                        }
                    }
                }
            }
        }
    }

}