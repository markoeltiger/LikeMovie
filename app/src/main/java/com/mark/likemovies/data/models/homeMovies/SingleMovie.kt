package com.mark.likemovies.data.models.homeMovies

import com.google.gson.annotations.SerializedName


data class SingleMovie(
    @SerializedName("casts")
    val casts: List<Cast>,
    @SerializedName("country_of_origin")
    val countryOfOrigin: String,
    @SerializedName("genres_ar")
    val genresAr: String,
    @SerializedName("genres_en")
    val genresEn: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("posters")
    val posters: List<Poster>,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("release_year")
    val releaseYear: Int,
    @SerializedName("story_ar")
    val storyAr: String,
    @SerializedName("story_en")
    val storyEn: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type_ar")
    val typeAr: String,
    @SerializedName("type_en")
    val typeEn: String,
    @SerializedName("type_id")
    val typeId: Int
)