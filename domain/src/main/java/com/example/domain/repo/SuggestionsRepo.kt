package com.mark.likemovies.Models

interface SuggestionsRepo {
  fun  getSuggestionsFromRemote():SuggestionsRes
}