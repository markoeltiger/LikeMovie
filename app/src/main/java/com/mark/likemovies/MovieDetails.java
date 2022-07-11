package com.mark.likemovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
TextView MovieName ,Movieyear,MovieCrew,MovieRate,MovieRatePeople,MovieGenre,MovieStory,heros,story;
FullMovie movieList;
String MovieId;
    androidx.appcompat.widget.Toolbar mTopToolbar;
    SimpleRatingBar simpleRatingBar;
    ProgressBar progressBar;
FloatingActionButton gohome;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.star) {
            Toast.makeText(MovieDetails.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
          mTopToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);

        Intent i = getIntent();
    MovieId=i.getStringExtra("movieid");
        currentMovie = (Item) i.getSerializableExtra("movieslist");
progressBar=findViewById(R.id.progressBar);
   //     simpleRatingBar=findViewById(R.id.simpleRatingBar);
        MovieName=findViewById(R.id.tvMovieName);
        Movieyear=findViewById(R.id.tvMovieYear);
        MovieCrew=findViewById(R.id.tvMovieCrew);
        MovieRate=findViewById(R.id.movieRate);
        gohome=findViewById(R.id.goHome);
//        MovieRatePeople=findViewById(R.id.tvmovieRatePepole);
        MovieGenre=findViewById(R.id.movieGenre);
        MovieStory=findViewById(R.id.MovieStory);
        heros=findViewById(R.id.heros);
story=findViewById(R.id.story);
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
heros.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        heros.setTextColor(Color.parseColor("#EF476f"));
        story.setTextColor(Color.parseColor("#000000"));
        MovieStory.setText(currentMovie.getCrew());
    }
});
        story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                story.setTextColor(Color.parseColor("#EF476f"));

                heros.setTextColor(Color.parseColor("#000000"));
                MovieStory.setText(movieList.getPlotLocal());
            }
        });
        MovieName.setText(currentMovie.getTitle());
        Movieyear.setText(currentMovie.getYear());
        MovieCrew.setText(currentMovie.getCrew());
        MovieRate.setText(currentMovie.getImDbRating()+ " / 10");
        progressBar.setMax(10);
        float f=Float.parseFloat(currentMovie.getImDbRating());
        double intrate = Double.parseDouble(currentMovie.getImDbRating());
        System.out.println(intrate);
        progressBar.setProgress((int) f);
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gomain = new Intent(MovieDetails.this,MainActivity.class);
                startActivity(gomain);
            }
        });
     //   MovieRatePeople.setText(currentMovie.getImDbRatingCount());

       // float f=Float.parseFloat(currentMovie.getImDbRating());
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