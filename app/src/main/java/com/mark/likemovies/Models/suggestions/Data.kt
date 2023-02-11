package com.mark.likemovies.Models.suggestions

data class Data(
    val casts: List<Cast>,
    val country_of_origin: String,
    val created_at: String,
    val genres_ar: String,
    val genres_en: String,
    val id: Int,
    val ignore_algorithm: Int,
    val posters: List<Poster>,
    val raters_count: Int,
    val rating: Double,
    val release_date: String,
    val release_year: Int,
    val story_ar: String,
    val story_en: String,
    val title: String,
    val type_ar: String,
    val type_en: String,
    val type_id: Int,
    val updated_at: String
)