package com.example.quickmart.data.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.quickmart.data.models.User

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: User)
}