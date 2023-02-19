package com.mark.likemovies.repo

import com.mark.likemovies.data.remote.RemoteApiService
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val apiService: RemoteApiService
){

}