package com.mark.likemovies.ui.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.mark.likemovies.R
import com.mark.likemovies.data.models.homeMovies.SingleMovie
import com.mark.likemovies.ui.auth.LoginActivity
import com.mark.likemovies.ui.auth.SignupActivity
import com.mark.likemovies.ui.details.DetailsActivity
import com.mark.likemovies.util.Constants
import com.mark.moviesexpert.ui.movie.MoviePagingAdapter
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), MoviePagingAdapter.OnItemClicked {
    lateinit var homeRecyclerView: RecyclerView
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    val movieAdapter = MoviePagingAdapter()
    var user_id = 0
    var user_name = "Guest"
    var user_email = "guest@gooodbad.com"
    val viewModel: HomeViewModel by viewModels()
    lateinit var sharedpreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        sharedpreferences = getSharedPreferences(
            Constants.USER_DETAILS_SharedPrefs,
            android.content.Context.MODE_PRIVATE
        )

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
//un default is zerrooo

            override fun onItemClick(position: Int, actionType: String, item: SingleMovie?) {
                if (isloggedin()) {
                    if (actionType == "details") {
                        val intent = Intent(this@HomeActivity, DetailsActivity::class.java)
                        intent.putExtra("data", item)
                        startActivity(intent)
                    } else if (actionType == "like") {
                        lifecycleScope.launch(Dispatchers.Main) {
                            try {
                                var likeReactResponse =
                                    viewModel.reactToEntertainment(0, "like", user_id, item!!.id)
                                if (likeReactResponse.isSuccessful ) {
                                    FancyToast.makeText(
                                        this@HomeActivity,
                                        "تم الإعجاب بالفيلم بنجاح",
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.SUCCESS,
                                        false
                                    ).show()
                                }
                            } catch (e: Exception) {
                                FancyToast.makeText(
                                    this@HomeActivity,
                                    "حدث خطأ أثناء الإعجاب بالفيلم برجاء المحاولة مرة اخري",
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.ERROR,
                                    false
                                ).show()

                            }

                        }
                    } else if (actionType == "dislike") {
                        lifecycleScope.launch(Dispatchers.Main) {
                            try {
                                var likeReactResponse =
                                    viewModel.reactToEntertainment(0, "like", user_id, item!!.id)
                                if (likeReactResponse.isSuccessful ) {
                                    FancyToast.makeText(
                                        this@HomeActivity,
                                        "تم الغاء الإعجاب بالفيلم بنجاح",
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.CONFUSING,
                                        false
                                    ).show()
                                }
                            } catch (e: Exception) {
                                FancyToast.makeText(
                                    this@HomeActivity,
                                    "حدث خطأ أثناء إلغاء الإعجاب بالفيلم برجاء المحاولة مرة اخري",
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.ERROR,
                                    false
                                ).show()

                            }

                        }
                    }


                } else {
                    FancyToast.makeText(
                        this@HomeActivity,
                        "You Must Login To Make an action",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,
                        false
                    ).show()
                    val signinintent = Intent(
                        this@HomeActivity,
                        LoginActivity::class.java
                    )
                    startActivity(signinintent)
                }
            }
        })


    }

    override fun onItemClick(position: Int, fileId: String, item: SingleMovie?) {
        TODO("Not yet implemented")
    }

    fun isloggedin(): Boolean {
        var logged = false
        logged = sharedpreferences.getBoolean("logged", false)
        if (logged) {
            user_id = sharedpreferences.getString("id", "0")?.toInt()!!
            user_email = sharedpreferences.getString("email", "guest@gooodbad.com")!!
            user_name = sharedpreferences.getString("username", "Guest")!!

        }
        return logged
    }
}



