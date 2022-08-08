package com.mark.likemovies.Models

data class Result(
    val contentRating: String,
    val description: String,
    val genreList: List<Genre>,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val imDbRatingVotes: String,
    val image: String,
    val metacriticRating: String,
    val plot: String,
    val runtimeStr: String,
    val starList: List<Star>,
    val stars: String,
    val title: String
)