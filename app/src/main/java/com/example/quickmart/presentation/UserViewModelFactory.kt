package com.example.quickmart.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickmart.data.database.UserDatabase
import com.example.quickmart.data.models.User
import com.example.quickmart.data.repositories.UserRepositoryImplementation

class UserViewModelFactory (
    private val context: Context,
    private val user: User
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(UserRepositoryImplementation(UserDatabase.getDatabase(context).userDao(), user)) as T
    }
}