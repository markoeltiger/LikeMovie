package com.mark.likemovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mark.likemovies.Adapter.movielikeadapter;
import com.mark.likemovies.Models.Item;

public class likedmovies extends AppCompatActivity {
    private RecyclerView recyclerView;
    movielikeadapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likedmovies);
        mbase
                = FirebaseDatabase.getInstance().getReference().child("movies").child("liked");

        recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
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
