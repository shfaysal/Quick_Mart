package com.example.quickmart.data.models


import kotlinx.serialization.Serializable

//@Serializable
data class Product(
    val availabilityStatus: String,
    val brand: String,
    val category: String,
    val description: String,
    val dimensions: Dimensions,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val meta: Meta,
    val minimumOrderQuantity: Int,
    val price: Double,
    val rating: Double,
    val returnPolicy: String,
    val reviews: List<Review>,
    val shippingInformation: String,
    val sku: String,
    val stock: Int,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val warrantyInformation: String,
    val weight: Int
){
    companion object {
        // Create a default or empty product instance
        val EMPTY = Product(
            availabilityStatus = "",
            brand = "",
            category = "",
            description = "",
            dimensions = Dimensions(0.0, 0.0, 0.0),
            discountPercentage = 0.0,
            id = 0,
            images = emptyList(),
            meta = Meta("", "", "", ""),
            minimumOrderQuantity = 0,
            price = 0.0,
            rating = 0.0,
            returnPolicy = "",
            reviews = emptyList(),
            shippingInformation = "",
            sku = "",
            stock = 0,
            tags = emptyList(),
            thumbnail = "",
            title = "",
            warrantyInformation = "",
            weight = 0
        )
    }
}