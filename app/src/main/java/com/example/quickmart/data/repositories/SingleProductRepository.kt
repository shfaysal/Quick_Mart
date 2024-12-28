package com.example.quickmart.data.repositories

import com.example.quickmart.data.models.Product
import kotlinx.coroutines.flow.Flow
import com.example.quickmart.data.Result


interface SingleProductRepository {
    suspend fun getSingleProduct() : Flow<Result<Product>>
}