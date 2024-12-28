package com.example.quickmart.data.models

data class ProductDs(
    val limit: Int,
    val products: List<ProductX>,
    val skip: Int,
    val total: Int
)