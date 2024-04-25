package com.example.quickmart.data.repositories

import com.example.quickmart.data.Result
import com.example.quickmart.data.models.User
import kotlinx.coroutines.flow.Flow

interface UserInfoGetRepository {
    suspend fun getUserDetail() : Flow<Result<User>>

}