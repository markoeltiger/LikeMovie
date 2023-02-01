package com.example.domain.entity.suggestions



 data class Genre(
     val id: Int,
     val key: String,
     val pivot: Pivot,
     val translations: List<Translation>,
     val value: String
)