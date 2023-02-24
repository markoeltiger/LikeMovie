package com.mark.moviesexpert.ui.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mark.likemovies.R
import com.mark.likemovies.data.models.homeMovies.SingleMovie
import com.squareup.picasso.Picasso

class MoviePagingAdapter :
    PagingDataAdapter<SingleMovie, MoviePagingAdapter.MovieViewHolder>(
        DIFF_UTIL
    ) {
    var onCLick: ((String) -> Unit)? = null

    private var mListener: OnItemClicked? = null

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<SingleMovie>() {
            override fun areItemsTheSame(
                oldItem: SingleMovie,
                newItem: SingleMovie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem:SingleMovie,
                newItem:SingleMovie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun onMovieClick(listener: (String) -> Unit) {
        onCLick = listener
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePoster = itemView.findViewById<ImageView>(R.id.mymoviposter)
        val movieDetails = itemView.findViewById<Button>(R.id.moreDetails)

    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
         val item = getItem(position)

         if (item!=null){
             holder.movieDetails.setOnClickListener {

                 mListener?.onItemClick(position, "details",item)

             }
             Picasso.get().load(item.posters.get(0).image).placeholder(R.drawable.app_logo_2)
                 .into(holder.moviePoster)



        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviePagingAdapter.MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.movieitem, parent, false)

        return MoviePagingAdapter.MovieViewHolder(view)
        TODO("Not yet implemented")
    }


    override fun getItemCount(): Int {
       // Log.e("getItemCount",itemCount.toString())
        return super.getItemCount()
    }
    fun setOnItemClickListener(clickListener: OnItemClicked) {
        this.mListener = clickListener
    }

    interface OnItemClicked {
        fun onItemClick(position: Int, fileId: String, item: SingleMovie?)
    }
}