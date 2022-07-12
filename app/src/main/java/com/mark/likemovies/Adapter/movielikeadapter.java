package com.mark.likemovies.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mark.likemovies.Models.Item;
import com.mark.likemovies.MovieDetails;
import com.mark.likemovies.R;
import com.squareup.picasso.Picasso;

public class movielikeadapter extends FirebaseRecyclerAdapter<Item, movielikeadapter.itemViewholder> {
    private LayoutInflater mInflater;
    private Context mcon;
    public movielikeadapter(Context context,
            @NonNull FirebaseRecyclerOptions<Item> options)
    {    super(options);
        this.mcon=context;

    }

    @Override
    protected void onBindViewHolder(@NonNull itemViewholder holder, int position, @NonNull Item model) {
        Picasso.get().load(model.getImage()).into(holder.imageView);
        holder.moviename.setText(model.getTitle());
holder.movierate.setText(model.getImDbRating());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mcon.startActivity(new Intent(mcon, MovieDetails.class));
        Intent detailsIntent = new Intent(mcon, MovieDetails.class);
        detailsIntent.putExtra("movieslist",model);
        detailsIntent.putExtra("movieid",model.getId());
        mcon.startActivity(detailsIntent);
    }
});
    }


    @NonNull
    @Override
    public itemViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.likedmovieitem, parent, false);
        return new movielikeadapter.itemViewholder(view);
    }
    class  itemViewholder
            extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView moviename, movierate, age;
        public itemViewholder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.avatar);
            moviename = itemView.findViewById(R.id.name);
            movierate = itemView.findViewById(R.id.rate);

        }
    }
}

