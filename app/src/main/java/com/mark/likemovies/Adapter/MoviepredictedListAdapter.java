package com.mark.likemovies.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.mark.likemovies.LoginActivity;
import com.mark.likemovies.Models.Item;
import com.mark.likemovies.Models.preidecteitem;
import com.mark.likemovies.MovieDetails;
import com.mark.likemovies.R;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;


public class MoviepredictedListAdapter extends RecyclerView.Adapter<MoviepredictedListAdapter.ViewHolder> {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    private List<preidecteitem> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mcon;
    SharedPreferences sharedpreferences;

    LinearLayoutManager manager;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

    // data is passed into the constructor
 public MoviepredictedListAdapter(Context context, List<preidecteitem> data, LinearLayoutManager manager) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mcon=context;
        this.manager=manager;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movieitem, parent, false);
        sharedpreferences =mcon.getSharedPreferences("sharedpreference", Context.MODE_PRIVATE);

        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(mData.get(position).getImage()).placeholder(R.drawable.movieposter).into(holder.posterImage);
// holder.rating.setText(mData.get(position).getImDbRating() +" / 10");
 holder.likImage.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
System.out.print("button is clicked");

         if (isloggedin()){     ref.child("users").child(currentFirebaseUser.getUid()).child("liked").child(mData.get(position).getId()).setValue(mData.get(position));
             manager.scrollToPosition(position+1);
             FancyToast.makeText(mcon,"The Movie Liked Successfuly",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
         }else {    FancyToast.makeText(mcon,"You Must Login To Make an action",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
             Intent gotologin = new Intent(mcon, LoginActivity.class);
             mcon.startActivity(gotologin);

         }



     }
 });
 holder.moreDetails.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         mcon.startActivity(new Intent(mcon, MovieDetails.class));
         Intent detailsIntent = new Intent(mcon, MovieDetails.class);
         Item item =new Item();
         System.out.print(mData.get(position).getTitle());

                 item.setImDbRating(mData.get(position).getImDbRating());
         item.setTitle(mData.get(position).getTitle());
         item.setCrew(mData.get(position).getCrew());
         item.setYear(mData.get(position).getYear());
         detailsIntent.putExtra("movieslist", item);
         detailsIntent.putExtra("movieid",mData.get(position).getId());
         detailsIntent.putExtra("position",position);

         mcon.startActivity(detailsIntent);
     }
 });
 FirebaseApp.initializeApp(mcon);

        holder.unlikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isloggedin()){     ref.child("users").child(currentFirebaseUser.getUid()).child("disliked").child(mData.get(position).getId()).setValue(mData.get(position));
                    manager.scrollToPosition(position+1);
                    FancyToast.makeText(mcon,"The Movie Disliked Successfuly",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                }else {    FancyToast.makeText(mcon,"You Must Login To Make an action",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    Intent gotologin = new Intent(mcon, LoginActivity.class);
                    mcon.startActivity(gotologin);

                }


            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public   class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
ImageView posterImage;
TextView rating;
Button moreDetails,likImage,unlikeImage;
        ViewHolder(View itemView) {
            super(itemView);
            posterImage=itemView.findViewById(R.id.mymoviposter);
            likImage=itemView.findViewById(R.id.likeMovieImage);
            unlikeImage=itemView.findViewById(R.id.unikeMovieImage);
//            rating=itemView.findViewById(R.id.rating);
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
                    if (isloggedin()){    ref.child("users").child(currentFirebaseUser.getUid()).child("disliked").child(mData.get(getAdapterPosition()).getId()).setValue(mData.get(getAdapterPosition()));

                        FancyToast.makeText(mcon,"The Movie DisLiked Successfuly",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                        manager.scrollToPosition(   getAdapterPosition()+1);
                        System.out.println("Movie Liked"+mData.get(getAdapterPosition()).getImage());}
                    else {FancyToast.makeText(mcon,"You Must Login To Make an Action",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                        Intent gotologin = new Intent(mcon, LoginActivity.class);
                        mcon.startActivity(gotologin);
                }}
            });
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    preidecteitem getItem(int id) {
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
    public boolean isloggedin(){
        boolean logged = false;
        logged  = sharedpreferences.getBoolean("logged",false);
        return logged;
    }
}