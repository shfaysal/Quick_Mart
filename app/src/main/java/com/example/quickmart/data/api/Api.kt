package com.example.quickmart.data.api

import com.example.quickmart.data.models.Product
import com.example.quickmart.data.models.Products
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @GET("/products")
    suspend fun getAllProducts() : Products

    @GET("/products/search")
    suspend fun getSearchProducts(
        @Query("q") name : String
    ): Products

    @POST("/products/add")
    suspend fun postProducts(
        @Body product: Product
    ): Product

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}