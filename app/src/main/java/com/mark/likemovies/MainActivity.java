package com.mark.likemovies;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.mark.likemovies.Adapter.MovieListAdapter;
import com.mark.likemovies.Client.ApiClient;
import com.mark.likemovies.Models.Item;
import com.mark.likemovies.Models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
Toolbar toolbar;
DrawerLayout drawerLayout;
NavigationView navigationView;
      RecyclerView recyclerView;
      List<Item> movieList;
    Item item;
    Movie moviecall;
MovieListAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerlayout);
        toolbar=(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setLayoutManager(layoutManager);
snapHelper.attachToRecyclerView(recyclerView);
        navigationView=findViewById(R.id.main_nav_view);

        toolbar.showOverflowMenu();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openNavigation,R.string.closeNavigation);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/en/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient myapiclient =retrofit.create(ApiClient.class);

        Call<Movie> mainCall =myapiclient.getTop250Movies();
        mainCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
               moviecall=response.body();
                movieList= moviecall.getItems();

                recyclerAdapter= new MovieListAdapter(MainActivity.this,movieList,layoutManager);
                recyclerView.setAdapter(recyclerAdapter);
                System.out.println("weather s" +response.raw().toString());

                System.out.println("weather 1" +movieList.size());



//
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                System.out.println("weather 0" +t.toString());

            }
        });




    }
}