package com.example.quickmart.data.repositories

import com.example.quickmart.data.Result
import com.example.quickmart.data.api.Api
import com.example.quickmart.data.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class SearchProductImplementation(
    private val api: Api,
    private val text: String
): ProductRepository {
    override suspend fun getProductList(): Flow<Result<List<Product>>> {
        return flow {
            val productFromApi = try {
                api.getSearchProducts(text)
            }catch (e : IOException) {
                e.printStackTrace()
                emit(Result.Error(message ="$e"))
                return@flow
            }

            emit(Result.Success(productFromApi.products))
        }
    }
}