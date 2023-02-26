package com.mark.likemovies.ui.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.mark.likemovies.Models.suggestions.SuggestionsResponse
import com.mark.likemovies.data.models.ReactionResponse.ReactionResponse
import com.mark.likemovies.data.remote.RemoteApiService
import com.mark.likemovies.repo.AuthRepo
import com.mark.likemovies.repo.FilterRepo
import com.mark.likemovies.repo.HomeRepo
import com.mark.moviesexpert.ui.movie.MoviePaging
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
public class FilterViewModel@Inject constructor(
    private val filterRepo: FilterRepo,
    private val moviesInterface: RemoteApiService

    ): ViewModel(){
    suspend fun getSuggestion(user_id:String,page:String,genre:String,country:String,from:String,to:String): Response<SuggestionsResponse> {
        return filterRepo.getSuggestion(user_id, page, genre, country, from, to)
    }
}