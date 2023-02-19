package com.mark.likemovies.repo

import com.mark.likemovies.data.models.register.RegisterResponse
import com.mark.likemovies.data.remote.RemoteApiService
import javax.inject.Inject

class AuthRepo  @Inject constructor(
    private val apiService:RemoteApiService
){
    suspend fun registerNewUser(name:String,email:String,password:String,conirmation_password:String): RegisterResponse {
        return apiService.RegisterNewUser(name,email,password,conirmation_password)
    }
}