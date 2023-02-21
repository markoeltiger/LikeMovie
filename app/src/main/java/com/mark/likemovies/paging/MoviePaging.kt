package com.mark.moviesexpert.ui.movie

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mark.likemovies.data.models.homeMovies.SingleMovie
import com.mark.likemovies.data.remote.RemoteApiService

import retrofit2.HttpException

import java.io.IOException

private const val TMDB_STARTING_PAGE_INDEX = 1

class MoviePaging( val moviesInterface: RemoteApiService) :
    PagingSource<Int, SingleMovie>() {
    override fun getRefreshKey(state: PagingState<Int, SingleMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SingleMovie> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {

                var data = moviesInterface.getMoviesFromAPI()
                val movies = data.data
                val nextKey =
                    if (movies.isEmpty()) {
                        null
                    } else {

                        pageIndex + (params.loadSize / 25)
                    }
                LoadResult.Page(
                    data = movies,
                    prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                    nextKey = nextKey?.plus(1)
                )



        } catch (exception: IOException) {

            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}