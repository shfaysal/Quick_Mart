package com.example.quickmart.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.quickmart.data.models.User

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Query("SELECT * FROM User WHERE id==1")
    suspend fun getUser(): User
}