package com.mark.likemovies.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.transition.Hold;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mark.likemovies.Models.Item;
import com.mark.likemovies.Models.Movie;
import com.mark.likemovies.MovieDetails;
import com.mark.likemovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mdatabase;
    private DatabaseReference fdatabase;
    private List<Item> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mcon;
    LinearLayoutManager manager;

    // data is passed into the constructor
 public    MovieListAdapter(Context context, List<Item> data, LinearLayoutManager manager) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mcon=context;
        this.manager=manager;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movieitem, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(mData.get(position).getImage()).into(holder.posterImage);
 holder.rating.setText(mData.get(position).getImDbRating() +" / 10");
 holder.likImage.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {





        manager.scrollToPosition(position+1);
     }
 });
 holder.moreDetails.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         mcon.startActivity(new Intent(mcon, MovieDetails.class));
         Intent detailsIntent = new Intent(mcon, MovieDetails.class);
         detailsIntent.putExtra("movieslist",mData.get(position));
         mcon.startActivity(detailsIntent);
     }
 });
holder.likImage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        System.out.println("likedMovie from view holder");
    }
});

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
ImageView posterImage,likImage,unlikeImage;
TextView rating;
Button moreDetails;
        ViewHolder(View itemView) {
            super(itemView);
            posterImage=itemView.findViewById(R.id.mymoviposter);
            likImage=itemView.findViewById(R.id.likeMovieImage);
            unlikeImage=itemView.findViewById(R.id.unikeMovieImage);
            rating=itemView.findViewById(R.id.rating);
            moreDetails=itemView.findViewById(R.id.moreDetails);
            // myTextView = itemView.findViewById(R.id.tvAnimalName);
            //itemView.setOnClickListener(this);
            likImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Movie Liked");
                }
            });
            unlikeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        getAdapterPosition();

                    System.out.println("Movie Liked");
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Item getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}