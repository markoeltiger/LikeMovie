package com.mark.likemovies.data.remote

import com.mark.likemovies.Models.APImodel
import com.mark.likemovies.data.models.homeMovies.HomeMoviesResponse
import com.mark.likemovies.data.models.register.RegisterResponse
import com.mark.likemovies.util.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RemoteApiService {
    @Headers("Accept: application/json",
        "Content-Type: application/json")
    @POST(Constants.REGISTER_ENDPOINT)
    suspend fun RegisterNewUser(
        @Query("name") name: String?,
        @Query("email") email: String?,
        @Query("password") password: String?,
        @Query("password_confirmation") password_confirmation: String?,
        ): Response<RegisterResponse>

    @GET(Constants.MAIN_ENTERTAINMENT_ENDPOINT)
    suspend  fun getMoviesFromAPI():
         Response<HomeMoviesResponse>
}
