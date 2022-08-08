package com.mark.likemovies.Models

data class MoviesDataClass(
    val errorMessage: Any,
    val queryString: String,
    val results: List<Result>
)