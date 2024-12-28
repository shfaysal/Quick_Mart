package com.example.quickmart.data.models

data class Review(
    val comment: String,
    val date: String,
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String
)