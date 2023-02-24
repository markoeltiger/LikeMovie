package com.mark.likemovies.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.mark.likemovies.data.remote.RemoteApiService
import com.mark.likemovies.repo.AuthRepo
import com.mark.likemovies.repo.HomeRepo
import com.mark.moviesexpert.ui.movie.MoviePaging
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
public class DetailsViewModel@Inject constructor(
    private val homeRepo: HomeRepo,
    private val moviesInterface: RemoteApiService

    ): ViewModel(){
    private val query = MutableLiveData<String>()
    val list = getSubtitles().cachedIn(viewModelScope)
    fun getSubtitles() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { MoviePaging(moviesInterface) }
    ).liveData
}