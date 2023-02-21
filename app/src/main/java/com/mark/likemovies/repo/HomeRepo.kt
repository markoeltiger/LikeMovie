package com.mark.likemovies.repo

import com.mark.likemovies.Models.APImodel
import com.mark.likemovies.data.models.register.RegisterResponse
import com.mark.likemovies.data.remote.RemoteApiService
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val apiService: RemoteApiService
){
    suspend fun getAllEnterTainments(): APImodel {
        return apiService.getMoviesFromAPI()
    }
}