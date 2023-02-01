package com.example.domain.entity.suggestions


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslationXX(
    @Json(name = "id")
    val id: Int,
    @Json(name = "locale")
    val locale: String,
    @Json(name = "type_id")
    val typeId: Int,
    @Json(name = "value")
    val value: String
)