package com.mark.likemovies.data.models.homeMovies

import com.google.gson.annotations.SerializedName


data class Cast(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)