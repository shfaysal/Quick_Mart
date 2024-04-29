package com.example.quickmart.data.repositories

import android.util.Log
import com.example.quickmart.data.Result
import com.example.quickmart.data.dao.UserDao
import com.example.quickmart.data.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class UserInfoGetRepositoryImplementation(
    private val dao : UserDao,
) : UserInfoGetRepository {
    override suspend fun getUserDetail(): Flow<Result<User>> {
        return flow {
            val userDetails = try {
                 dao.getUser()
            }catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "something wrong here"))
                return@flow
            }
//            Log.d("TAG",userDetails.name)
            emit(Result.Success(userDetails))
        }
    }
}