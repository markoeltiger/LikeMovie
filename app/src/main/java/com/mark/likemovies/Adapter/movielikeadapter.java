package com.mark.likemovies.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mark.likemovies.Models.Item;
import com.mark.likemovies.MovieDetails;
import com.mark.likemovies.R;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;

public class movielikeadapter extends FirebaseRecyclerAdapter<Item, movielikeadapter.itemViewholder> {
    private LayoutInflater mInflater;
    private Context mcon;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

    public movielikeadapter(Context context,
            @NonNull FirebaseRecyclerOptions<Item> options)
    {    super(options);
        this.mcon=context;

    }

    @Override
    protected void onBindViewHolder(@NonNull itemViewholder holder, int position, @NonNull Item model) {
      Picasso.get().load(model.getImage()).into(holder.imageView);
      //        Picasso.get().load(mData.get(position).getImage()).placeholder(R.drawable.movieposter).into(holder.posterImage);
//        holder.moviename.setText(model.getTitle());
//holder.movierate.setText(model.getImDbRating());
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
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseApp.initializeApp(mcon);


                ref.child("users").child(currentFirebaseUser.getUid()).child("liked").child(model.getId()).removeValue();
                FancyToast.makeText(mcon,"تم إزالة الفيلم من الإعجابات",FancyToast.LENGTH_LONG,FancyToast.INFO,true);
            }
        });
holder.butn.setOnClickListener(new View.OnClickListener() {
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
        Button butn;
        Button remove;
        TextView moviename, movierate, age;
        public itemViewholder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.avatar);
              butn = itemView.findViewById(R.id.mybutton);
            remove = itemView.findViewById(R.id.remove);
//            movierate = itemView.findViewById(R.id.rate);

        }
    }
}

