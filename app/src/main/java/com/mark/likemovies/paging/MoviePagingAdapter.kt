package com.mark.moviesexpert.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mark.likemovies.R
import com.mark.likemovies.data.models.homeMovies.SingleMovie

class MoviePagingAdapter :
    PagingDataAdapter<SingleMovie, MoviePagingAdapter.MovieViewHolder>(
        DIFF_UTIL
    ) {
    var onCLick: ((String) -> Unit)? = null

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
        val moviePoster = itemView.findViewById<ImageView>(R.id.movieImageView)

    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
         val item = getItem(position)
        if (item!=null){
            Glide.with(holder.moviePoster.context)
                .load(item.posters.get(0).image)
                .placeholder(R.drawable.movieposter)
                .into(holder.moviePoster)


        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviePagingAdapter.MovieViewHolder {
        TODO("Not yet implemented")
    }


}