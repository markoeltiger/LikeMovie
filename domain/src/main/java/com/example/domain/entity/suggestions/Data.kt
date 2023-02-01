package com.example.domain.entity.suggestions



 data class Data(
     val coefficient: Double,
     val coefficientUpdatedAt: String,
     val countryOfOrigin: String,
     val createdAt: String,
     val genres: List<Genre>,
     val genresAr: String,
     val genresEn: String,
     val id: Int,
     val ignoreAlgorithm: Int,
     val ratersCount: Int,
     val rating: Double,
     val releaseDate: String,
     val releaseYear: Int,
     val story: String,
     val storyAr: String,
     val storyEn: String,
     val title: String,
     val translations: List<TranslationX>,
     val type: Type,
     val typeAr: String,
     val typeEn: String,
     val typeId: Int,
     val updatedAt: String
)