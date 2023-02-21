package com.mark.likemovies.data.models.homeMovies


import com.google.gson.annotations.SerializedName

 data class HomeMoviesResponse(
     @SerializedName("current_page")
    val currentPage: Int,
     @SerializedName("data")
    val `data`: List<SingleMovie>,
     @SerializedName("first_page_url")
    val firstPageUrl: String,
     @SerializedName("from")
    val from: Int,
     @SerializedName("last_page")
    val lastPage: Int,
     @SerializedName("last_page_url")
    val lastPageUrl: String,
     @SerializedName("links")
    val links: List<Link>,
     @SerializedName("next_page_url")
    val nextPageUrl: String,
     @SerializedName("path")
    val path: String,
     @SerializedName("per_page")
    val perPage: Int,
     @SerializedName("prev_page_url")
    val prevPageUrl: String,
     @SerializedName("to")
    val to: Int,
     @SerializedName("total")
    val total: Int
)