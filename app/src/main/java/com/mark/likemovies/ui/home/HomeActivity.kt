package com.mark.likemovies.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.mark.likemovies.MovieDetails
import com.mark.likemovies.R
import com.mark.likemovies.data.models.homeMovies.SingleMovie
import com.mark.likemovies.ui.details.DetailsActivity
import com.mark.moviesexpert.ui.movie.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), MoviePagingAdapter.OnItemClicked {
    lateinit var homeRecyclerView: RecyclerView
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    val movieAdapter = MoviePagingAdapter()
    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()
        setupMainRv()
        setupObservers()
    }


    fun setupObservers() {
        viewModel.list.observe(this, Observer {

            movieAdapter.submitData(lifecycle, it)
        })
    }
    fun setupMainRv() {
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        homeRecyclerView.setLayoutManager(layoutManager)

        snapHelper.attachToRecyclerView(homeRecyclerView)
        homeRecyclerView.adapter = movieAdapter

    }
    fun initViews() {
        homeRecyclerView = findViewById(R.id.recyclerview)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerlayout)
        toolbar = findViewById<View>(R.id.toolbar2) as Toolbar
        setSupportActionBar(toolbar)
        movieAdapter.setOnItemClickListener(object : MoviePagingAdapter.OnItemClicked {


            override fun onItemClick(position: Int, fileId: String, item: SingleMovie?) {
                if (fileId == "details") {
                    val intent = Intent(this@HomeActivity, DetailsActivity::class.java)
                    intent.putExtra("data", item)
                    startActivity(intent)
                }


            }
        })


    }

    override fun onItemClick(position: Int, fileId: String, item: SingleMovie?) {
        TODO("Not yet implemented")
    }
}



