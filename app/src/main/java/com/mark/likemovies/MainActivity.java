package com.mark.likemovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        FirebaseApp.initializeApp(MainActivity.this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setLayoutManager(layoutManager);
snapHelper.attachToRecyclerView(recyclerView);
        navigationView=findViewById(R.id.main_nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();

                    if (id == R.id.Nav_likes) {
                        Intent likesintent =new Intent(MainActivity.this,likedmovies.class);
                        startActivity(likesintent);
                    }
              return false;   }
            });
        }

        toolbar.showOverflowMenu();

        ViewCompat.setLayoutDirection(toolbar, ViewCompat.LAYOUT_DIRECTION_RTL);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openNavigation,R.string.closeNavigation){
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                System.out.println("ok");
                if(item != null && item.getItemId() == android.R.id.home){
                    if (drawerLayout.isDrawerOpen((Gravity.LEFT))) {
                        drawerLayout.closeDrawer(Gravity.LEFT);
                    } else {
                        drawerLayout.openDrawer(Gravity.LEFT);
                    }
                }
                return false;
            }
        };

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
                FirebaseApp.initializeApp(MainActivity.this);
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