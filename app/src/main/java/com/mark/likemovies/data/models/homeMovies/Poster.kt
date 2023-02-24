package com.mark.likemovies.data.models.homeMovies

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Poster(
    @SerializedName("entertainment_id")
    val entertainmentId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
): Serializable