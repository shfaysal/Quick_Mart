package com.example.quickmart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickmart.data.Result
import com.example.quickmart.data.models.User
import com.example.quickmart.data.repositories.UserInfoGetRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserInfoGetViewModel (
    private val userInfoGetRepository: UserInfoGetRepository
) : ViewModel() {

    private val _userDetails = MutableStateFlow<User?>(null)
    val userDetails = _userDetails.asStateFlow()

    private val _showErrorTestChannel = Channel<Boolean>()
    val showErrorTestChannel = _showErrorTestChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            userInfoGetRepository.getUserDetail().collectLatest {result ->

                when(result) {
                    is Result.Error -> {
                        _showErrorTestChannel.send(false)
                    }

                    is Result.Success -> {
                        result.data?.let {user ->
                            _userDetails.update {
//                                Log.d("TAG",user.name)
                                user
                            }
                        }
                    }
                }
            }
        }
    }


}