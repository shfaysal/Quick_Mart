package com.example.quickmart.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val mobile: String,
    val address: String,
    val gender: String,
    val age: Int,
    val birthdate: String,
    val photo: ByteArray
)
