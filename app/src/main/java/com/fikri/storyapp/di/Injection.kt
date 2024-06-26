package com.fikri.storyapp.di

import android.content.Context
import com.fikri.storyapp.data.UserRepository
import com.fikri.storyapp.data.pref.UserPreference
import com.fikri.storyapp.data.pref.dataStore
import com.fikri.storyapp.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(apiService, pref)
    }
}