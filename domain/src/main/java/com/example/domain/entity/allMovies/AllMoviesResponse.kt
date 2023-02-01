package com.example.domain.entity.allMovies



 data class AllMoviesResponse(
     val currentPage: Int?,
     val `data`: List<Data?>?,
     val firstPageUrl: String?,
     val from: Int?,
     val lastPage: Int?,
     val lastPageUrl: String?,
     val links: List<Link?>?,
     val nextPageUrl: String?,
     val path: String?,
     val perPage: Int?,
     val prevPageUrl: Any?,
     val to: Int?,
     val total: Int?
)