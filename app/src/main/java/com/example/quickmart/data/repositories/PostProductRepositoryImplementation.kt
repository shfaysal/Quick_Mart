package com.example.quickmart.data.repositories

import com.example.quickmart.data.Result
import com.example.quickmart.data.api.Api
import com.example.quickmart.data.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class PostProductRepositoryImplementation(
    private val api : Api,
    private val product: Product
) : PostProductRepository {
    override suspend fun postProduct(): Flow<Result<Product>> {
        return flow {
            val postResponseFromApi = try {
                api.postProduct(product)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "$e"))
                return@flow
            }

            emit(Result.Success(postResponseFromApi))
        }
    }


}