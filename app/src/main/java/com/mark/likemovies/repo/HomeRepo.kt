package com.mark.likemovies.repo

import com.mark.likemovies.Models.APImodel
import com.mark.likemovies.data.models.ReactionResponse.ReactionResponse
import com.mark.likemovies.data.models.homeMovies.HomeMoviesResponse
import com.mark.likemovies.data.models.register.RegisterResponse
import com.mark.likemovies.data.remote.RemoteApiService
import retrofit2.Response
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val apiService: RemoteApiService
){
    suspend fun getAllEnterTainments(): Response<HomeMoviesResponse> {
        return apiService.getMoviesFromAPI()
    }
    suspend fun reactToEntertainment(un:Int,reaction:String,user_id:Int,entertainment_id:Int): Response<ReactionResponse> {
        return apiService.React(un, reaction, user_id, entertainment_id)
    }
}