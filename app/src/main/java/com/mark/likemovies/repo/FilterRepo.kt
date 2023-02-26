package com.mark.likemovies.repo

import com.mark.likemovies.Models.APImodel
import com.mark.likemovies.Models.suggestions.SuggestionsResponse
import com.mark.likemovies.data.models.ReactionResponse.ReactionResponse
import com.mark.likemovies.data.models.homeMovies.HomeMoviesResponse
import com.mark.likemovies.data.models.register.RegisterResponse
import com.mark.likemovies.data.remote.RemoteApiService
import retrofit2.Response
import javax.inject.Inject

class FilterRepo @Inject constructor(
    private val apiService: RemoteApiService
){

    suspend fun getSuggestion(user_id:Int,page:String,genre:String,country:String,from:String,to:String): Response<SuggestionsResponse> {
        return apiService.getSuggestions(user_id,page, genre,  country,from, to)
    }
}