package com.mark.likemovies.Client

import com.google.android.gms.common.api.Response
import com.mark.likemovies.Models.MoviesDataClass
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("AdvancedSearch/k_cg74shwp/")
    suspend fun getQuotes(@Query("genres")  genres:String) : retrofit2.Response<MoviesDataClass>

}