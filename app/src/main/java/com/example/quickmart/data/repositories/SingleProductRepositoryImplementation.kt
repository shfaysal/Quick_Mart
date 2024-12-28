package com.example.quickmart.data.repositories

import coil.network.HttpException
import com.example.quickmart.data.Result
import com.example.quickmart.data.api.Api
import com.example.quickmart.data.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class SingleProductRepositoryImplementation(
    private val api : Api,
    private val id : Int
) : SingleProductRepository{
    override suspend fun getSingleProduct(): Flow<Result<Product>> {
        return flow {
            val productFromApi = try {
                api.getProductById(id)
            }catch (e : IOException){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            } catch (e: HttpException){
                e.printStackTrace()
                emit(Result.Error(message = "Error is httpException"))
                return@flow
            }

            emit(Result.Success(productFromApi))
        }
    }

}