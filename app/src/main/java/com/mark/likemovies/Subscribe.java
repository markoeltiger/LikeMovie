package com.mark.likemovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.mark.likemovies.Adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;

public class Subscribe extends AppCompatActivity {
    RecyclerView recyclerView;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        MyRecyclerViewAdapter adapter;
        lottieAnimationView=findViewById(R.id.button);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add(" خطه سنويه");
        animalNames.add("اقتراحات يوميه غير محدوده");
        animalNames.add("تخصيص اقتراحات");
        animalNames.add("بدون اعلانات");
        recyclerView= findViewById(R.id.advantagerecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, animalNames);

        recyclerView.setAdapter(adapter);
        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    }
