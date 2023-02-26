package com.mark.likemovies.ui.filter

import androidx.lifecycle.ViewModel
import com.mark.likemovies.data.models.homeMovies.SingleMovie
import com.mark.likemovies.data.remote.RemoteApiService
import com.mark.likemovies.repo.FilterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
public class FilterViewModel@Inject constructor(
    private val filterRepo: FilterRepo,
    private val moviesInterface: RemoteApiService

    ): ViewModel(){
    suspend fun getSuggestion(
        user_id: Int,
        page: Int,
        genre:String,
        country:String,
        from:String,
        to:String): Response<SingleMovie> {
        return filterRepo.getSuggestion(user_id, page, genre, country, from, to)
    }
}