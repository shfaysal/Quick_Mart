package com.example.quickmart.data.repositories

import com.example.quickmart.data.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun postUserDetail(): Flow<Result<Boolean>>
}