package com.mark.likemovies;

import static android.content.ContentValues.TAG;

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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mark.likemovies.Adapter.MovieListAdapter;
import com.mark.likemovies.Client.ApiClient;
import com.mark.likemovies.Models.Item;
import com.mark.likemovies.Models.Movie;
import com.squareup.picasso.Picasso;

import java.util.Collections;
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
    SharedPreferences sharedpreferences;
    Item item;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    DatabaseReference mbase;
    Movie moviecall;
MovieListAdapter recyclerAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.ideamenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.idea) {
           Intent idea = new Intent(MainActivity.this,FilterActivity.class);
           startActivity(idea);
            return true;
        }

        if (id == R.id.filter) {
            Intent idea = new Intent(MainActivity.this,FilterActivity.class);
            startActivity(idea);
            return true;
        }
       return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerlayout);
        toolbar=(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(
                MainActivity.this);
          sharedpreferences = getSharedPreferences("sharedpreference", Context.MODE_PRIVATE);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setLayoutManager(layoutManager);
if (isloggedin()){     mbase
        = FirebaseDatabase.getInstance().getReference().child("users").child(currentFirebaseUser.getUid());
}else{   mbase
        = FirebaseDatabase.getInstance().getReference().child("users").child("guest");}

snapHelper.attachToRecyclerView(recyclerView);
        navigationView=findViewById(R.id.main_nav_view);
        if (!isloggedin()){    navigationView.inflateMenu(R.menu.notloggedin);}else {navigationView.inflateMenu(R.menu.mainmenu);}

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();

                    if (id == R.id.Nav_likes) {
                        Intent likesintent =new Intent(MainActivity.this,likedmovies.class);
                        startActivity(likesintent);
                    }
                    if (id == R.id.Nav_Login) {
                        Intent loginintent =new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(loginintent);
                    }
                    if (id == R.id.Nav_signup) {
                        Intent signupintent =new Intent(MainActivity.this,SignupActivity.class);
                        startActivity(signupintent);
                    }
                    if (id == R.id.Nav_Premuim) {
                        Intent Subscribeintent =new Intent(MainActivity.this,Subscribe.class);
                        startActivity(Subscribeintent);
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
        View headerview =navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerview.findViewById(R.id.userName);
        TextView number = (TextView) headerview.findViewById(R.id.userIPhone);

        mbase.child("phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                number.setText(value);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        mbase.child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                navUsername.setText(value);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

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
                Collections.shuffle(movieList);
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
    public boolean isloggedin(){
        boolean logged = false;
        logged  = sharedpreferences.getBoolean("logged",false);
        return logged;
    }
}