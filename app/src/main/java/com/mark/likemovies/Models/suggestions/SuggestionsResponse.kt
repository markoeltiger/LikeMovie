package com.mark.likemovies.Models.suggestions

data class SuggestionsResponse(
    val `data`: Data,
    val message: String,
    val status: Boolean
)