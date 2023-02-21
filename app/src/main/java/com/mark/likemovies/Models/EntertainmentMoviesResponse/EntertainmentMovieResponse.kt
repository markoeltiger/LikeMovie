package com.mark.likemovies.Models.EntertainmentMoviesResponse


 data class EntertainmentMovieResponse(
     var currentPage: Int?,
     var `data`: List<Data?>?,

    var firstPageUrl: String?,

    var from: Int?,
         var lastPage: Int?,
     var lastPageUrl: String?,
     var links: List<Link?>?,
     var nextPageUrl: String?,
     var path: String?,
     var perPage: Int?,
     var prevPageUrl: Any?,
     var to: Int?,
     var total: Int?
)