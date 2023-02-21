package com.mark.likemovies.Models.EntertainmentMoviesResponse



 data class Data(
     var casts: List<Cast?>?,
     var countryOfOrigin: String?,
     var genresAr: String?,
     var genresEn: String?,
     var id: Int?,
     var posters: List<Poster?>?,
     var rating: Double?,
     var releaseYear: Int?,
     var storyAr: String?,
     var storyEn: String?,
     var title: String?,
     var typeAr: String?,
     var typeEn: String?,
     var typeId: Int?
)