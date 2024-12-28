package com.example.quickmart.data.api

import com.example.quickmart.data.models.Product
import com.example.quickmart.data.models.Products
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("/products")
    suspend fun getAllProducts() : Products

    @GET("/products/search")
    suspend fun getSearchProducts(
        @Query("q") name : String
    ): Products

    @POST("/products/add")
    suspend fun postProduct(
        @Body product: Product
    ): Product

    @GET("/products/{id}")
    suspend fun getProductById(
        @Path("id") id : Int
    ) : Product

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}