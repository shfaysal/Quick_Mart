package com.example.quickmart.data.repositories

import com.example.quickmart.data.Result
import com.example.quickmart.data.dao.UserDao
import com.example.quickmart.data.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class UserRepositoryImplementation (
    private val dao: UserDao,
    private val user: User
) : UserRepository {
    override suspend fun getUserDetail(): Flow<Result<Boolean>> {
        return flow {
             try {
                 dao.upsertUser(user)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = e.toString()))
                return@flow
            }

            emit(Result.Success(true))
        }
    }

}