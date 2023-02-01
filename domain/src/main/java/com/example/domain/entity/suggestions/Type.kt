package com.example.domain.entity.suggestions


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Type(
    @Json(name = "id")
    val id: Int,
    @Json(name = "key")
    val key: String,
    @Json(name = "translations")
    val translations: List<TranslationXX>,
    @Json(name = "value")
    val value: String
)