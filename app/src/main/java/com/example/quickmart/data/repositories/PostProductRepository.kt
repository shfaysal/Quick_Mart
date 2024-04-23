package com.example.quickmart.data.repositories

import com.example.quickmart.data.Result
import com.example.quickmart.data.models.Product
import kotlinx.coroutines.flow.Flow

interface PostProductRepository {
    suspend fun postProduct(): Flow<Result<Product>>
}