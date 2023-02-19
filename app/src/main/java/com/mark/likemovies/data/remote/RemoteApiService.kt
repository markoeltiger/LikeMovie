package com.mark.likemovies.data.remote

import com.mark.likemovies.Models.APImodel
import com.mark.likemovies.data.models.register.RegisterResponse
import com.mark.likemovies.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {
    @GET(Constants.REGISTER_ENDPOINT)
    suspend fun RegisterNewUser(
        @Query("name") name: String?,
        @Query("email") email: String?,
        @Query("password") password: String?,
        @Query("password_confirmation") password_confirmation: String?,
        ): RegisterResponse

    @GET(Constants.REGISTER_ENDPOINT)
    fun getMoviesFromAPI():
            APImodel
}
