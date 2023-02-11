package com.mark.likemovies.Client

import com.google.android.gms.common.api.Response
import com.mark.likemovies.Models.MoviesDataClass
import com.mark.likemovies.Models.suggestions.SuggestionsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("/api/suggest")
    suspend fun getSuggestions(

        @Query("user_id ") user_id: String,
        @Query("page") page: String,
        @Query("genre") genre: String,
        @Query("country") country: String,
        @Query("from") from: String,
        @Query("to") to: String,


        )
    : retrofit2.Response<SuggestionsResponse>

}