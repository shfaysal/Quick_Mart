package com.example.quickmart.data.repositories

import com.example.quickmart.data.Result
import com.example.quickmart.data.api.Api
import com.example.quickmart.data.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ProductRepositoryImplementation (
    private val api : Api
): ProductRepository {
    override suspend fun getProductList(): Flow<Result<List<Product>>> {
        return flow {
            val productsFromApi = try {
                api.getAllProducts()
            }catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading Products"))
                return@flow
            }catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error is httpException"))
                return@flow
            }

            emit(Result.Success(productsFromApi.products))
        }
    }


}