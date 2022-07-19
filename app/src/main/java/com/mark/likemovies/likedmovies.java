package com.mark.likemovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mark.likemovies.Adapter.movielikeadapter;
import com.mark.likemovies.Models.Item;

public class likedmovies extends AppCompatActivity {
    private RecyclerView recyclerView;
    Toolbar toolbar;

    movielikeadapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase;
    NavigationView navigationView;

    DrawerLayout drawerLayout;
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
            //process your onClick here
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likedmovies);
        drawerLayout=findViewById(R.id.drawerlayout);
        toolbar=(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        mbase
                = FirebaseDatabase.getInstance().getReference().child("movies").child("liked");

        recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(likedmovies.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
snapHelper.attachToRecyclerView(recyclerView);
        FirebaseRecyclerOptions<Item> options
                = new FirebaseRecyclerOptions.Builder<Item>()
                .setQuery(mbase, Item.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new movielikeadapter(likedmovies.this,options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
    }
