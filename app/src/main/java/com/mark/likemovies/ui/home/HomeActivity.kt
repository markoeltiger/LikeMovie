package com.mark.likemovies.ui.home

import android.os.Bundle
import android.view.View
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
import com.mark.likemovies.R
import com.mark.moviesexpert.ui.movie.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
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

    fun initViews() {
        homeRecyclerView = findViewById(R.id.recyclerview)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerlayout)
        toolbar = findViewById<View>(R.id.toolbar2) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun setupMainRv() {
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        homeRecyclerView.setLayoutManager(layoutManager)

        snapHelper.attachToRecyclerView(homeRecyclerView)
        homeRecyclerView.adapter = movieAdapter

    }
}



