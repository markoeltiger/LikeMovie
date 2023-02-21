package com.mark.likemovies.Client;

 import com.mark.likemovies.Models.API.Response;
import com.mark.likemovies.Models.APImodel;
 import com.mark.likemovies.Models.EntertainmentMoviesResponse.EntertainmentMovieResponse;
 import com.mark.likemovies.Models.FullMovie;
import com.mark.likemovies.Models.Movie;
import com.mark.likemovies.Models.MoviesDataClass;
 import com.mark.likemovies.Models.Register.RegisterResponse;
 import com.mark.likemovies.Models.predictedmovie;

 import org.androidannotations.annotations.rest.Post;

 import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface ApiClient {
    @GET("Top250Movies/k_wfn8g9ur")

    public Call<Movie> getTop250Movies();
    @GET("api/film/get")

    public Call<Response> getMoviesCall(@Query("page") int page);
    @GET("Title/k_wfn8g9ur/{p1}")
    public Call<FullMovie> getMovieDetails(@Path("p1") String id);
    @GET("MostPopularMovies/k_wfn8g9ur")

    public Call<predictedmovie> getMostPopularMovies();
    @GET("AdvancedSearch/k_wfn8g9ur")
     public Call<MoviesDataClass> getSepecificMovies(@Query("Genre") String id);
    @GET("api/entertainment/get")
    public Call<APImodel> getMoviesFromAPI();
    @Post("/api/auth/register")
    public Call<RegisterResponse> registerUser(
            @Query("name") String name,
            @Query("email") String email,
            @Query("password") String password,
            @Query("password_confirmation") String password_confirmation
    );


}
