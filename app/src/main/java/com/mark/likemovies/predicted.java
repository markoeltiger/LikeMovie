package com.mark.likemovies;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.Toast;

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
import com.mark.likemovies.Adapter.MoviepredictedListAdapter;
import com.mark.likemovies.Client.ApiClient;
import com.mark.likemovies.Models.Item;
import com.mark.likemovies.Models.Movie;
import com.mark.likemovies.Models.predictedmovie;
import com.mark.likemovies.Models.preidecteitem;
import com.mark.likemovies.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class predicted extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView recyclerView;
    List<preidecteitem> movieList;
    preidecteitem item;
    SharedPreferences sharedpreferences;
    DatabaseReference mbase;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
int position=0;
    predictedmovie moviecall;
    MoviepredictedListAdapter recyclerAdapter;

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
            if (isloggedin()){        Intent idea = new Intent(predicted.this,FilterActivity.class);
                startActivity(idea);
                return true;}else{

                FancyToast.makeText(predicted.this,"You Must Login To Make an action",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                Intent gotologin = new Intent(predicted.this, LoginActivity.class);
                startActivity(gotologin);
            }

        }

        if (id == R.id.filter) {
            if (isloggedin()){




                FancyToast.makeText(predicted.this,"Added To Loved Movies",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                item.setIcon(R.drawable.ic_baseline_favorite_24);

                return true;}else{

                FancyToast.makeText(predicted.this,"You Must Login To Make an action",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                Intent gotologin = new Intent(predicted.this, LoginActivity.class);
                startActivity(gotologin);
            }

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predicted);
        drawerLayout=findViewById(R.id.drawerlayout);
        toolbar=(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(
                predicted.this);
        sharedpreferences = getSharedPreferences("sharedpreference", Context.MODE_PRIVATE);
        if (isloggedin()){     mbase
                = FirebaseDatabase.getInstance().getReference().child("users").child(currentFirebaseUser.getUid());
        }else{   mbase
                = FirebaseDatabase.getInstance().getReference().child("users").child("guest");}
        Intent intent = getIntent();

        String type = intent.getStringExtra("type");
        if (type=="suggestions"){
            Toast.makeText(predicted.this,"suggestions",Toast.LENGTH_SHORT).show();
        }

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(predicted.this, LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setLayoutManager(layoutManager);

        snapHelper.attachToRecyclerView(recyclerView);
        navigationView=findViewById(R.id.main_nav_view);
        if (!isloggedin()){    navigationView.inflateMenu(R.menu.notloggedin);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();

                    System.out.print(id);
                    if (id == R.id.Nav_Logins) {
                        Intent loginintent =new Intent(predicted.this,LoginActivity.class);
                        startActivity(loginintent);
                    }
                    if (id == R.id.Nav_signups) {
                        Intent signupintent =new Intent(predicted.this,SignupActivity.class);
                        startActivity(signupintent);
                    }
                    return false;
                }
            });


        }else {navigationView.inflateMenu(R.menu.mainmenu);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    System.out.print(id+"ass");

                    if (id == R.id.Nav_likes) {
                        Intent likesintent =new Intent(predicted.this,likedmovies.class);
                        likesintent.putExtra("type","liked");

                        startActivity(likesintent);
                    }

                    if (id == R.id.Nav_Premuim) {
                        Intent Subscribeintent =new Intent(predicted.this,Subscribe.class);
                        startActivity(Subscribeintent);
                    }
                    if (id == R.id.Nav_waiting_list) {
                        Intent waitingintent =new Intent(predicted.this,likedmovies.class);
                        waitingintent.putExtra("type","loved");

                        startActivity(waitingintent);
                    }
                    return false;   }
            });

        }

        View headerview =navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerview.findViewById(R.id.userName);
        TextView number = (TextView) headerview.findViewById(R.id.userIPhone);
        Intent i = getIntent();
        supportInvalidateOptionsMenu();
        position=i.getIntExtra("position",0);
        mbase.child("email").addValueEventListener(new ValueEventListener() {
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

        Call<predictedmovie> mainCall =myapiclient.getMostPopularMovies();
        mainCall.enqueue(new Callback<predictedmovie>() {
            @Override
            public void onResponse(Call<predictedmovie> call, Response<predictedmovie> response) {
                moviecall=response.body();
                movieList= moviecall.getItems();
             //   Collections.shuffle(movieList);
                FirebaseApp.initializeApp(predicted.this);
                recyclerAdapter= new MoviepredictedListAdapter(predicted.this,movieList,layoutManager);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.scrollToPosition(position);
                
                System.out.println("weather s" +response.raw().toString());

                System.out.println("weather 1" +movieList.size());



//
            }

            @Override
            public void onFailure(Call<predictedmovie> call, Throwable t) {
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