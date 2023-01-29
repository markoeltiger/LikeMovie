package com.mark.likemovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.mark.likemovies.Adapter.MovieListAdapter;
import com.mark.likemovies.Adapter.TabFragmentAdapter;
import com.mark.likemovies.Client.ApiClient;
import com.mark.likemovies.Fragments.FragmentHero;
import com.mark.likemovies.Fragments.FragmentStory;
import com.mark.likemovies.Models.FullMovie;
import com.mark.likemovies.Models.Item;
import com.mark.likemovies.Models.Movie;
import com.shashank.sony.fancytoastlib.FancyToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetails extends AppCompatActivity {
    private Item currentMovie;
    TextView MovieName, Movieyear, MovieCrew, MovieRate, MovieRatePeople, MovieGenre, MovieStory, heros, story;
    FullMovie movieList;
    String MovieId;
    androidx.appcompat.widget.Toolbar mTopToolbar;
    TabLayout mTabs;
    private Menu menu;
    int position;
    View mIndicator;
    private int indicatorWidth;
    SimpleRatingBar simpleRatingBar;
    ViewPager mViewPager;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    ProgressBar progressBar;
    FloatingActionButton gohome;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detailstoolbar, menu);
        this.menu = menu;
        //   supportInvalidateOptionsMenu();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "I Found this Movie : " + currentMovie.getTitle() + " In This Great App " +
                    "https://play.google.com/store/apps/details?id=com.mark.likemovies&hl=en&gl=US");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            Toast.makeText(MovieDetails.this, "lets share", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.love) {

            item.setIcon(R.drawable.ic_baseline_favorite_24);

            ref.child("users").child(currentFirebaseUser.getUid()).child("loved").child(MovieId).setValue(currentMovie);


            FancyToast.makeText(MovieDetails.this, "The Movie Added Successfuly", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        mTopToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        mTopToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my = new Intent(MovieDetails.this, predicted.class);
                startActivity(my);
            }
        });
        mTabs = findViewById(R.id.tab);
        mIndicator = findViewById(R.id.indicator);
        mViewPager = findViewById(R.id.viewPager);
        Intent i = getIntent();
        supportInvalidateOptionsMenu();
        position = i.getIntExtra("position", 0);
        MovieId = i.getStringExtra("movieid");
        currentMovie = (Item) i.getSerializableExtra("movieslist");
        progressBar = findViewById(R.id.progressBar);
        TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentHero.newInstance(), "الأبطال");
        adapter.addFragment(FragmentStory.newInstance(), "القصة");
        mViewPager.setAdapter(adapter);
        mTabs.addTab(mTabs.newTab().setText("الأبطال"), 0);
        mTabs.addTab(mTabs.newTab().setText("القصة"), 1, true);
        mTabs.setBackground(getResources().getDrawable(R.drawable.tabcustomselector));
        mTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.select();
                tab.view.setBackground(getResources().getDrawable(R.drawable.gradient_bg));
                if (tab.getPosition() == 0) {
                    MovieStory.setText(currentMovie.getCrew());
                } else {
                    try {
                        MovieStory.setText(movieList.getPlotLocal());
                    } catch (Exception e) {

                    }

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.view.setBackground(getResources().getDrawable(R.drawable.tab_bg));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //   mTabs.setupWithViewPager(mViewPager);
        //     simpleRatingBar=findViewById(R.id.simpleRatingBar);
        MovieName = findViewById(R.id.tvMovieName);
        Movieyear = findViewById(R.id.tvMovieYear);
        MovieCrew = findViewById(R.id.tvMovieCrew);
        MovieRate = findViewById(R.id.movieRate);
        gohome = findViewById(R.id.goHome);
//        MovieRatePeople=findViewById(R.id.tvmovieRatePepole);
        MovieGenre = findViewById(R.id.movieGenre);
        MovieStory = findViewById(R.id.MovieStory);
        heros = findViewById(R.id.heros);


        story = findViewById(R.id.story);
        mTabs.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = mTabs.getWidth() / 2;

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });
        ref.child("users").child(currentFirebaseUser.getUid()).child("loved").child(MovieId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    System.out.print("wsh");
                    try {
                        menu.getItem(0).setIcon(ContextCompat.getDrawable(MovieDetails.this, R.drawable.ic_baseline_favorite_24));

                    } catch (Exception e) {
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/ar/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient myapiclient = retrofit.create(ApiClient.class);
        //System.out.println("Mark"+currentMovie.getId());
        Call<FullMovie> mainCall = myapiclient.getMovieDetails(MovieId);
        mainCall.toString();
        mainCall.enqueue(new Callback<FullMovie>() {
            @Override
            public void onResponse(Call<FullMovie> call, Response<FullMovie> response) {

                movieList = response.body();
                System.out.println("weather s" + response.raw().toString());
                MovieGenre.setText(movieList.getGenres());
                MovieStory.setText(movieList.getPlotLocal());

            }

            @Override
            public void onFailure(Call<FullMovie> call, Throwable t) {
                System.out.println("weather" + t.getMessage());
                System.out.println("weather" + t.getCause());
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
        MovieRate.setText(currentMovie.getImDbRating() + " / 10");
        MovieDetails.this.setTitle(currentMovie.getYear());

        progressBar.setMax(10);
        float f = Float.parseFloat(currentMovie.getImDbRating());
        double intrate = Double.parseDouble(currentMovie.getImDbRating());
        System.out.println(intrate);

        progressBar.setProgress((int) f);
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gomain = new Intent(MovieDetails.this, MainActivity.class);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MovieDetails.this, predicted.class);
        intent.putExtra("position", position);
        startActivity(intent);
        super.onBackPressed();
        finish();


    }

}