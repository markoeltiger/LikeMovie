package com.example.domain.entity.suggestions


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pivot(
    @Json(name = "entertainment_id")
    val entertainmentId: Int,
    @Json(name = "genre_id")
    val genreId: Int
)