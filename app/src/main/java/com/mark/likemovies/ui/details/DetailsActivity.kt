package com.mark.likemovies.ui.details

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mark.likemovies.R
import com.mark.likemovies.data.models.homeMovies.SingleMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    lateinit var NameTV: TextView
    lateinit var movieGenres: TextView
    lateinit var tvMovieCrew: TextView
    lateinit var movieRate: TextView
    lateinit var heros: TextView
    lateinit var story: TextView
    lateinit var MovieStory: TextView
    var mTopToolbar: Toolbar? = null
    lateinit var currentData :SingleMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        initViews()
        getAndSetData()
    }




    fun initViews() {
        NameTV = findViewById(R.id.tvMovieName)
        mTopToolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        setSupportActionBar(mTopToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }


    fun getAndSetData() {
        currentData = (intent.getSerializableExtra("data") as? SingleMovie)!!
        NameTV.text=currentData.title

    }
}



