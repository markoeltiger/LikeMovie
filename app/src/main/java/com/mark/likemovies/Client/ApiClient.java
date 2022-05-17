package com.mark.likemovies.Client;

import com.mark.likemovies.Models.FullMovie;
import com.mark.likemovies.Models.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("Top250Movies/k_wfn8g9ur")

    public Call<Movie> getTop250Movies();
    @GET("Title/k_wfn8g9ur/")
    public Call<FullMovie> getMovieDetails(@Query("id") String id);

}
