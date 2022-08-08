package com.mark.likemovies.Client;

import com.mark.likemovies.Models.FullMovie;
import com.mark.likemovies.Models.Movie;
import com.mark.likemovies.Models.MoviesDataClass;
import com.mark.likemovies.Models.predictedmovie;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface ApiClient {
    @GET("Top250Movies/k_cg74shwp")

    public Call<Movie> getTop250Movies();
    @GET("Title/k_cg74shwp/{p1}")
    public Call<FullMovie> getMovieDetails(@Path("p1") String id);
    @GET("MostPopularMovies/k_cg74shwp")

    public Call<predictedmovie> getMostPopularMovies();
    @GET("AdvancedSearch/k_cg74shwp")
     public Call<MoviesDataClass> getSepecificMovies(@Query("Genre") String id);
}
