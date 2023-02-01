package com.example.domain.entity.allMovies




 data class Data(
     val casts: List<Cast?>?,
     val countryOfOrigin: String?,
     val genresAr: String?,
     val genresEn: String?,
     val id: Int?,
     val posters: List<Poster?>?,
     val rating: Double?,
     val releaseYear: Int?,
     val storyAr: String?,
     val storyEn: String?,
     val title: String?,
     val typeAr: String?,
     val typeEn: String?,
     val typeId: Int?
)