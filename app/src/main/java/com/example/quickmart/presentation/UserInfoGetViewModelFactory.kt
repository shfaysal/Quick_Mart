package com.example.quickmart.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickmart.data.database.UserDatabase
import com.example.quickmart.data.models.User
import com.example.quickmart.data.repositories.UserInfoGetRepositoryImplementation

class UserInfoGetViewModelFactory (
    private val context: Context,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserInfoGetViewModel(UserInfoGetRepositoryImplementation(UserDatabase.getDatabase(context).userDao()))
                as T
    }
}