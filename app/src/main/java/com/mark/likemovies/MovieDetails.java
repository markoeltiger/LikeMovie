package com.mark.likemovies;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
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
TextView MovieName ,Movieyear,MovieCrew,MovieRate,MovieRatePeople,MovieGenre,MovieStory;
FullMovie movieList;
String MovieId;
    SimpleRatingBar simpleRatingBar;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent i = getIntent();
    MovieId=i.getStringExtra("movieid");
        currentMovie = (Item) i.getSerializableExtra("movieslist");

   //     simpleRatingBar=findViewById(R.id.simpleRatingBar);
        MovieName=findViewById(R.id.tvMovieName);
        Movieyear=findViewById(R.id.tvMovieYear);
        MovieCrew=findViewById(R.id.tvMovieCrew);
        MovieRate=findViewById(R.id.movieRate);
//        MovieRatePeople=findViewById(R.id.tvmovieRatePepole);
        MovieGenre=findViewById(R.id.movieGenre);
        MovieStory=findViewById(R.id.MovieStory);
progressBar.setMax(10);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/ar/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient myapiclient =retrofit.create(ApiClient.class);
        //System.out.println("Mark"+currentMovie.getId());
        Call<FullMovie> mainCall =myapiclient.getMovieDetails(MovieId);
        mainCall.toString();
        mainCall.enqueue(new Callback<FullMovie>() {
                             @Override
                             public void onResponse(Call<FullMovie> call, Response<FullMovie> response) {

                                movieList =response.body();
                                 System.out.println("weather s" +response.raw().toString());
                                 MovieGenre.setText(movieList.getGenres());
                                 MovieStory.setText(movieList.getPlotLocal());

                             }

                             @Override
                             public void onFailure(Call<FullMovie> call, Throwable t) {
                                 System.out.println("weather" +t.getMessage());
                                 System.out.println("weather" +t.getCause());
                             }
                         });

        MovieName.setText(currentMovie.getTitle());
        Movieyear.setText(currentMovie.getYear());
        MovieCrew.setText(currentMovie.getCrew());
        MovieRate.setText(currentMovie.getImDbRating()+ " / 10");
        progressBar.setProgress(Integer.valueOf(currentMovie.getImDbRating()));
     //   MovieRatePeople.setText(currentMovie.getImDbRatingCount());

        float f=Float.parseFloat(currentMovie.getImDbRating());
//        SimpleRatingBar.AnimationBuilder builder = simpleRatingBar.getAnimationBuilder()
//                .setRatingTarget(f)
//                .setAnimatorListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                })
//                ;
//
//        builder.start();
//        builder.setRatingTarget(f);
    }
}