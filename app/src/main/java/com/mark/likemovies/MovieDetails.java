package com.mark.likemovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mark.likemovies.Adapter.MovieListAdapter;
import com.mark.likemovies.Client.ApiClient;
import com.mark.likemovies.Models.FullMovie;
import com.mark.likemovies.Models.Item;
import com.mark.likemovies.Models.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetails extends AppCompatActivity {
private Item currentMovie;
TextView MovieName ,Movieyear,MovieCrew,MovieRate,MovieRatePeople,MovieGenre;
FullMovie movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent i = getIntent();
        currentMovie = (Item) i.getSerializableExtra("movieslist");

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/ar/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient myapiclient =retrofit.create(ApiClient.class);
        Call<FullMovie> mainCall =myapiclient.getMovieDetails(currentMovie.getId());
        mainCall.enqueue(new Callback<FullMovie>() {
                             @Override
                             public void onResponse(Call<FullMovie> call, Response<FullMovie> response) {
                                movieList =response.body();

                             }

                             @Override
                             public void onFailure(Call<FullMovie> call, Throwable t) {

                             }
                         });

        MovieName=findViewById(R.id.tvMovieName);
        Movieyear=findViewById(R.id.tvMovieYear);
        MovieCrew=findViewById(R.id.tvMovieCrew);
        MovieRate=findViewById(R.id.movieRate);
        MovieRatePeople=findViewById(R.id.tvmovieRatePepole);
        MovieName.setText(currentMovie.getTitle());
        Movieyear.setText(currentMovie.getYear());
        MovieCrew.setText(currentMovie.getCrew());
        MovieRate.setText(currentMovie.getImDbRating()+ " / 10");
        MovieRatePeople.setText(currentMovie.getImDbRatingCount());
        MovieGenre.setText(movieList.getGenres());
    }
}