package com.mark.likemovies.Models

data class Data(
    val casts: List<Cast>,
    val country_of_origin: String,
    val genres_ar: String,
    val genres_en: String,
    val id: Int,
    val posters: List<Poster>,
    val rating: String,
    val release_year: String,
    val story_ar: String,
    val story_en: String,
    val title: String,
    val type_ar: String,
    val type_en: String,
    val type_id: String
)