package com.example.domain.entity.suggestions


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslationX(
    @Json(name = "entertainment_id")
    val entertainmentId: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "locale")
    val locale: String,
    @Json(name = "story")
    val story: String
)